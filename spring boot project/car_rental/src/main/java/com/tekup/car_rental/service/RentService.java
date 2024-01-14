package com.tekup.car_rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tekup.car_rental.model.CarState;
import com.tekup.car_rental.model.Rent;
import com.tekup.car_rental.model.RentState;
import com.tekup.car_rental.repository.RentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentService implements IRentService {
    private final RentRepository rentRepository;

    @Override
    public Rent addRent(Rent rent) {
        rent.getCar().setState(CarState.RENT);
        return rentRepository.save(rent);
    }

    @Override
    public Rent updateRent(Long id, Rent rent) {
        Rent updateRent = rentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " not found"));
        if (rent.getState() == RentState.ONGOING)
            rent.getCar().setState(CarState.RENT);
        else
            rent.getCar().setState(CarState.AVAILABLE);
        updateRent.setCar(rent.getCar());
        updateRent.setClient(rent.getClient());
        updateRent.setState(rent.getState());
        updateRent.setAddedBy(rent.getAddedBy());
        return rentRepository.save(updateRent);
    }

    @Override
    public void deleteRent(Rent rent) {
        rent.getCar().setState(CarState.AVAILABLE);
        rentRepository.delete(rent);
    }

    @Override
    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    @Override
    public Optional<Rent> getRentById(Long id) {
        return rentRepository.findById(id);
    }

}
