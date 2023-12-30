package com.tekup.car_rental.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ClientDta
 */
@Data
public class ClientDto {

    @NotNull
    @Min(value = 10000000, message = "need to be exact 8 digit")
    @Max(value = 99999999, message = "need to be exact 8 digit")
    private Integer cin;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}