package com.tekup.car_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekup.car_rental.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
