package com.tekup.car_rental.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.tekup.car_rental.model.CarState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarDto {

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufactureYear;

    @NotNull
    private Double pricePerNight;

    @NotNull
    private CarState state;

    @NotNull
    private MultipartFile image;
}
