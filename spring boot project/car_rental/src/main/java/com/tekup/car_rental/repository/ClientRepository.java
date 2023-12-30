package com.tekup.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.car_rental.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
