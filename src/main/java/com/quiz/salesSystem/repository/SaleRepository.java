package com.quiz.salesSystem.repository;

import com.quiz.salesSystem.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}