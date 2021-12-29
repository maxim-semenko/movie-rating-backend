package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.dto.RequestPaymentDTO;
import com.max.movierating.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = APIConstant.PAYMENT_API)
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN') and #requestPaymentDTO.userId == authentication.principal.id")
    public ResponseEntity<Boolean> pay(@Valid @RequestBody RequestPaymentDTO requestPaymentDTO) {
        return new ResponseEntity<>(paymentService.pay(requestPaymentDTO), HttpStatus.OK);
    }

}
