package com.quiz.salesSystem.DTO;


import lombok.Data;
import java.util.List;

@Data
public class SaleRequest {
    private Long clientId;
    private String seller;
    private List<SaleTransactionRequest> transactions;
}