package com.tekup.car_rental.service;

import java.util.List;
import java.util.Optional;

import com.tekup.car_rental.model.Car;

public interface ICarService {
    public Car addCar(Car car);

    public Car updateCar(Long id, Car car);

    public void deleteCar(Car car);

    public List<Car> getAllCars();

    public Optional<Car> getCarById(Long id);
}
