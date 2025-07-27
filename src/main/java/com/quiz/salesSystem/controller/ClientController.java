package com.quiz.salesSystem.controller;

import com.quiz.salesSystem.DTO.ClientDTO;
import com.quiz.salesSystem.model.Client;
import com.quiz.salesSystem.model.Product;
import com.quiz.salesSystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientDTO client) {
        try {
            Client created = clientService.createClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client creation failed: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client updated = clientService.updateClient(id, client);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
}
