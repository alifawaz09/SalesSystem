package com.quiz.salesSystem.DTO;

import lombok.Data;

@Data
public class SaleTransactionRequest {
    private Long productId;
    private int quantity;
    private double price;
}