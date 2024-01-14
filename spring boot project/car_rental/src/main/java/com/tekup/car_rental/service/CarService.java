package com.tekup.car_rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tekup.car_rental.model.Car;
import com.tekup.car_rental.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService implements ICarService {
    private final CarRepository carRepository;

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, Car car) {
        Car updatedCar = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(id + " not found"));
        updatedCar.setBrand(car.getBrand());
        updatedCar.setModel(car.getModel());
        updatedCar.setManufactureYear(car.getManufactureYear());
        updatedCar.setPricePerNight(car.getPricePerNight());
        if (car.getImage() != null)
            updatedCar.setImage(car.getImage());
        return carRepository.save(updatedCar);
    }

    @Override
    public void deleteCar(Car car) {
        carRepository.delete(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

}
