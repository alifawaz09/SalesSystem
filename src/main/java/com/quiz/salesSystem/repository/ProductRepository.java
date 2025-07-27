package com.quiz.salesSystem.repository;

import com.quiz.salesSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
