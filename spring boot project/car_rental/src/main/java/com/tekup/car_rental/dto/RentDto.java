package com.tekup.car_rental.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.tekup.car_rental.model.RentState;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentDto {
    @NotNull
    private Long clientId;

    @NotNull
    private Long carId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull
    private RentState state;
}
