package com.tekup.car_rental.service;

import java.util.List;
import java.util.Optional;

import com.tekup.car_rental.model.Client;

public interface IClientService {
    public Client addClient(Client client);

    public Client updateClient(Long id, Client client);

    public void deleteClient(Client client);

    public List<Client> getAllClients();

    public Optional<Client> getClientById(Long id);
}
