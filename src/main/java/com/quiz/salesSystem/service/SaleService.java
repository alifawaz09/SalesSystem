package com.quiz.salesSystem.service;

import com.quiz.salesSystem.DTO.SaleRequest;
import com.quiz.salesSystem.DTO.SaleTransactionRequest;
import com.quiz.salesSystem.model.*;
import com.quiz.salesSystem.repository.ClientRepository;
import com.quiz.salesSystem.repository.ProductRepository;
import com.quiz.salesSystem.repository.SaleRepository;
import com.quiz.salesSystem.repository.SaleTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {
    private static final Logger log = LoggerFactory.getLogger(SaleService.class);


    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleTransactionRepository saleTransactionRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale createSale(SaleRequest saleRequest) {
        Client client = clientRepository.findById(saleRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Sale sale = new Sale();
        sale.setClient(client);
        sale.setSeller(saleRequest.getSeller());
        sale.setCreationDate(LocalDateTime.now());

        List<SaleTransaction> transactions = new ArrayList<>();
        double total = 0;

        for (SaleTransactionRequest tr : saleRequest.getTransactions()) {
            Product product = productRepository.findById(tr.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            SaleTransaction transaction = new SaleTransaction();
            transaction.setProduct(product);
            transaction.setQuantity(tr.getQuantity());
            transaction.setPrice(tr.getPrice());
            transaction.setSale(sale);

            transactions.add(transaction);
            total += tr.getQuantity() * tr.getPrice();
        }

        sale.setTransactions(transactions);
        sale.setTotal(total);

        return saleRepository.save(sale);

    }

    public Sale updateTransactionDetails(Long transactionId, int quantity, double price) {
        SaleTransaction transaction = saleTransactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        TransactionLog transactionLog = TransactionLog.builder()
                .transactionId(transaction.getId())
                .timestamp(LocalDateTime.now())
                .oldQuantity(transaction.getQuantity())
                .newQuantity(quantity)
                .oldPrice(transaction.getPrice())
                .newPrice(price)
                .build();

        log.info("Transaction Log: {}", transactionLog);

        transaction.setQuantity(quantity);
        transaction.setPrice(price);
        saleTransactionRepository.save(transaction);

        return transaction.getSale();
    }
}