package com.max.movierating.service;

import com.max.movierating.dto.entity.RequestPaymentDTO;

public interface PaymentService {

    Boolean pay(RequestPaymentDTO requestPaymentDTO);
}
