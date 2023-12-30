package com.tekup.car_rental.service;

import java.util.List;
import java.util.Optional;

import com.tekup.car_rental.model.Rent;

public interface IRentService {
    public Rent addRent(Rent rent);

    public Rent updateRent(Long id, Rent rent);

    public void deleteRent(Rent rent);

    public List<Rent> getAllRents();

    public Optional<Rent> getRentById(Long id);
}
