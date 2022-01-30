package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.dto.RequestPaymentDTO;
import com.max.movierating.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for payments requests.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@RestController
@RequestMapping(value = APIConstant.PAYMENT_API)
@Slf4j
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER') and #requestPaymentDTO.userId == authentication.principal.id")
    public ResponseEntity<Boolean> pay(@RequestBody RequestPaymentDTO requestPaymentDTO) {
//        log.info(requestPaymentDTO.getCardHolderName());
//        log.info(requestPaymentDTO.getExpirationMMYY());
//        log.info(String.valueOf(requestPaymentDTO.getCVV()));
        log.info(String.valueOf(requestPaymentDTO.getEmailCode()));
        log.info(String.valueOf(requestPaymentDTO.getUserId()));
        return new ResponseEntity<>(paymentService.pay(requestPaymentDTO), HttpStatus.OK);
    }

}
