package com.tekup.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.car_rental.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
