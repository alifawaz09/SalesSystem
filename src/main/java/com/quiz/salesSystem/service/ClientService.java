package com.quiz.salesSystem.service;

import com.quiz.salesSystem.DTO.ClientDTO;
import com.quiz.salesSystem.model.Client;
import com.quiz.salesSystem.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(ClientDTO client) {
        Client newClient = new Client();
        newClient.setName(client.getName());
        newClient.setLastName(client.getLastName());
        newClient.setMobile(client.getMobile());
        return clientRepository.save(newClient);
    }

    public Client updateClient(Long id, Client updatedClient) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setName(updatedClient.getName());
                    client.setLastName(updatedClient.getLastName());
                    client.setMobile(updatedClient.getMobile());
                    return clientRepository.save(client);
                })
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
