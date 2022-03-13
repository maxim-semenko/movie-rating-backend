package com.max.movierating.service.impl;

import com.max.movierating.dto.entity.RequestPaymentDTO;
import com.max.movierating.entity.Basket;
import com.max.movierating.entity.Film;
import com.max.movierating.entity.PurchaseStorage;
import com.max.movierating.entity.Transaction;
import com.max.movierating.entity.TransactionStatus;
import com.max.movierating.entity.User;
import com.max.movierating.entity.enums.MessageTypeEnum;
import com.max.movierating.entity.enums.TransactionStatusEnum;
import com.max.movierating.entity.mail.MailCode;
import com.max.movierating.entity.mail.MailTypeMessage;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.BasketRepository;
import com.max.movierating.repository.MailCodeRepository;
import com.max.movierating.repository.PurchaseStorageRepository;
import com.max.movierating.repository.TransactionRepository;
import com.max.movierating.repository.TransactionStatusRepository;
import com.max.movierating.service.PaymentService;
import com.max.movierating.service.impl.mail.MailTypeMessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * Payment Service implementation that realize PaymentService interface {@link PaymentService}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final UserServiceImpl userService;
    private final MailTypeMessageServiceImpl mailTypeMessageService;
    private final BasketRepository basketRepository;
    private final MailCodeRepository mailCodeRepository;
    private final PurchaseStorageRepository purchaseStorageRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionStatusRepository transactionStatusRepository;

    @Autowired
    public PaymentServiceImpl(UserServiceImpl userService,
                              MailTypeMessageServiceImpl mailTypeMessageService,
                              BasketRepository basketRepository,
                              MailCodeRepository mailCodeRepository,
                              PurchaseStorageRepository purchaseStorageRepository,
                              TransactionRepository transactionRepository,
                              TransactionStatusRepository transactionStatusRepository) {
        this.userService = userService;
        this.mailTypeMessageService = mailTypeMessageService;
        this.basketRepository = basketRepository;
        this.mailCodeRepository = mailCodeRepository;
        this.purchaseStorageRepository = purchaseStorageRepository;
        this.transactionRepository = transactionRepository;
        this.transactionStatusRepository = transactionStatusRepository;
    }

    @Transactional
    @Override
    public Boolean pay(RequestPaymentDTO requestPaymentDTO) {
        User user = userService.findById(requestPaymentDTO.getUserId());
        MailTypeMessage mailTypeMessage = mailTypeMessageService.findByName(MessageTypeEnum.PAYMENT_ORDER.toString());
        Optional<MailCode> optionalMailCode = mailCodeRepository.getLastByUserAndType(user, mailTypeMessage);

        if (optionalMailCode.isPresent()) {
            MailCode mailCode = optionalMailCode.get();
            if (requestPaymentDTO.getEmailCode().equals(mailCode.getCode()) && Boolean.TRUE.equals(mailCode.getIsValid())) {
                Basket basket = user.getBasket();
                Set<Film> films = basket.getFilmList();
                PurchaseStorage purchaseStorage = user.getPurchaseStorage();
                TransactionStatus transactionStatus =
                        transactionStatusRepository.findTransactionStatusByName(TransactionStatusEnum.COMPLETE.toString());

                Transaction transaction = Transaction.builder()
                        .user(user)
                        .summa(basket.getSumma())
                        .transactionStatus(transactionStatus)
                        .date(new Date())
                        .build();

                transaction.getFilmList().addAll(films);
                purchaseStorage.getFilmList().addAll(films);

                purchaseStorageRepository.save(purchaseStorage);
                transactionRepository.save(transaction);

                basket.getFilmList().removeAll(films);
                basket.setSumma(0.0);
                basketRepository.save(basket);
            } else {
                throw new BadRequestException("Mail code not valid");
            }
        } else {
            throw new ResourceNotFoundException("Mail code not found");
        }

        return true;
    }
}
