package com.max.movierating.service;

import com.max.movierating.entity.PurchaseStorage;

public interface PurchaseStorageService {

    PurchaseStorage findByUserId(Long userId);

}
