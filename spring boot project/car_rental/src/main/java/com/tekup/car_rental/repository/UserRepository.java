package com.tekup.car_rental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tekup.car_rental.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
