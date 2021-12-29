package com.max.movierating.service;

import com.max.movierating.dto.RequestPaymentDTO;

public interface PaymentService {

    Boolean pay(RequestPaymentDTO requestPaymentDTO);
}
