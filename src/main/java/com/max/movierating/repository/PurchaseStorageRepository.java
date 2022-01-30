package com.max.movierating.repository;

import com.max.movierating.entity.PurchaseStorage;
import com.max.movierating.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseStorageRepository extends JpaRepository<PurchaseStorage, Long> {

    Optional<PurchaseStorage> findPurchaseStorageByUser(User user);

    void deletePurchaseStorageByUser(User user);
}
