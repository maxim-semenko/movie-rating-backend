package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.entity.PurchaseStorage;
import com.max.movierating.service.impl.PurchaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = APIConstant.PURCHASE_API)
public class PurchaseStorageController {

    private final PurchaseStorageServiceImpl purchaseStorageService;

    @Autowired
    public PurchaseStorageController(PurchaseStorageServiceImpl purchaseStorageService) {
        this.purchaseStorageService = purchaseStorageService;
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('USER') and #id == authentication.principal.id")
    public ResponseEntity<PurchaseStorage> findById(@PathVariable Long id) {
        return new ResponseEntity<>(purchaseStorageService.findByUserId(id), HttpStatus.OK);
    }
}
