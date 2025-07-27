package com.quiz.salesSystem.controller;


import com.quiz.salesSystem.DTO.SaleRequest;
import com.quiz.salesSystem.model.Sale;
import com.quiz.salesSystem.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    @Autowired
    private SaleService saleService;


    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody SaleRequest saleRequest) {
        try {
            Sale createdSale = saleService.createSale(saleRequest);
            return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/transactions/{transactionId}")
    public ResponseEntity<Sale> updateTransaction(
            @PathVariable Long transactionId,
            @RequestParam int quantity,
            @RequestParam double price
    ) {
        try {
        Sale updatedSale = saleService.updateTransactionDetails(transactionId, quantity, price);
        return ResponseEntity.ok(updatedSale);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
