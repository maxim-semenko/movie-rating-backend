package com.max.movierating.repository;

import com.max.movierating.entity.PurchaseStorage;
import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PurchaseStorageRepository for working with entity {@link PurchaseStorage}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface PurchaseStorageRepository extends JpaRepository<PurchaseStorage, Long> {

    Optional<PurchaseStorage> findPurchaseStorageByUser(User user);

    void deletePurchaseStorageByUser(User user);
}
