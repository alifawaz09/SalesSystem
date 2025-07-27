package com.quiz.salesSystem.repository;

import com.quiz.salesSystem.model.SaleTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleTransactionRepository extends JpaRepository<SaleTransaction, Long> {
}