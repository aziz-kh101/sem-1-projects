package com.tekup.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.car_rental.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Long> {

}
