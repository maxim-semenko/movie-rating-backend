package com.max.movierating.service.impl;

import com.max.movierating.dto.RequestSendMessageDTO;
import com.max.movierating.entity.mail.MailCode;
import com.max.movierating.entity.mail.MailMessage;
import com.max.movierating.entity.mail.MailTypeMessage;
import com.max.movierating.entity.User;
import com.max.movierating.repository.MailCodeRepository;
import com.max.movierating.service.MailMessageServiceImpl;
import com.max.movierating.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Long userId = requestSendMessageDTO.getUserId();
        String typeMessage = requestSendMessageDTO.getTypeMessage();

        User user = userService.findById(userId);
        MailTypeMessage mailTypeMessage = mailTypeMessageService.findByName(typeMessage);
        MailMessage mailMessage = mailMessageService.findByMailTypeMessage(mailTypeMessage);

        Integer code = GenerateCodeUtil.generateCode();
        String emailTo = user.getEmail();
        String text = String.format(mailMessage.getText(), user.getUsername(), code);
        String subject = mailMessage.getSubject();
        senderMailService.sendMessage(emailTo, subject, text);

        MailCode lastMailCode = mailCodeRepository.getLastByUserAndType(user, mailTypeMessage);
        if (lastMailCode != null) {
            lastMailCode.setIsValid(false);
            mailCodeRepository.save(lastMailCode);
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
