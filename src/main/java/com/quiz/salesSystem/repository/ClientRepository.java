package com.quiz.salesSystem.repository;

import com.quiz.salesSystem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
