package com.tekup.car_rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tekup.car_rental.model.Client;
import com.tekup.car_rental.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Client updateClient = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " not found"));
        updateClient.setCin(client.getCin());
        updateClient.setFirstName(client.getFirstName());
        updateClient.setLastName(client.getLastName());
        return clientRepository.save(updateClient);
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

}
