package com.max.movierating.service.impl;

import com.max.movierating.dto.RequestSendMessageDTO;
import com.max.movierating.entity.User;
import com.max.movierating.entity.mail.MailCode;
import com.max.movierating.entity.mail.MailMessage;
import com.max.movierating.entity.mail.MailTypeMessage;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.repository.MailCodeRepository;
import com.max.movierating.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Mail Service implementation that realize MailService interface {@link MarkService}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private final SenderMailServiceImpl senderMailService;
    private final UserServiceImpl userService;
    private final MailTypeMessageServiceImpl mailTypeMessageService;
    private final MailMessageServiceImpl mailMessageService;
    private final MailCodeRepository mailCodeRepository;

    @Autowired
    public MailServiceImpl(SenderMailServiceImpl senderMailService,
                           UserServiceImpl userService,
                           MailTypeMessageServiceImpl mailTypeMessageService,
                           MailMessageServiceImpl mailMessageService,
                           MailCodeRepository mailCodeRepository) {
        this.senderMailService = senderMailService;
        this.userService = userService;
        this.mailTypeMessageService = mailTypeMessageService;
        this.mailMessageService = mailMessageService;
        this.mailCodeRepository = mailCodeRepository;
    }

    @Override
    public Boolean performMessage(RequestSendMessageDTO requestSendMessageDTO) {
        User user = userService.findById(requestSendMessageDTO.getUserId());
        MailTypeMessage mailTypeMessage = mailTypeMessageService.findByName(requestSendMessageDTO.getTypeMessage());
        MailMessage mailMessage = mailMessageService.findByMailTypeMessage(mailTypeMessage);

        Integer code = GeneratorUtil.generateCode(1000, 9999);
        String emailTo = user.getEmail();
        String text = String.format(mailMessage.getText(), user.getUsername(), code);
        String subject = mailMessage.getSubject();
        senderMailService.sendMessage(emailTo, subject, text);

        Optional<MailCode> optionalLastMailCode = mailCodeRepository.getLastByUserAndType(user, mailTypeMessage);
        if (optionalLastMailCode.isPresent() && optionalLastMailCode.get().getIsValid()) {
            MailCode lastMailCode = optionalLastMailCode.get();
            lastMailCode.setIsValid(false);
            mailCodeRepository.save(lastMailCode);
        } else {
            log.error("Valid mail code not found");
            throw new BadRequestException("Valid mail code not found");
        }

        mailCodeRepository.save(
                MailCode.builder()
                        .user(user)
                        .code(code)
                        .mailTypeMessage(mailTypeMessage)
                        .build());

        return true;
    }

}
