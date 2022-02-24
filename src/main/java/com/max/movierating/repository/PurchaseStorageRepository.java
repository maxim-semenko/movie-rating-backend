package com.max.movierating.repository;

import com.max.movierating.entity.PurchaseStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PurchaseStorageRepository for working with entity {@link PurchaseStorage}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface PurchaseStorageRepository extends JpaRepository<PurchaseStorage, Long> {
}
