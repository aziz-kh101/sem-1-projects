package com.tekup.car_rental.dto;

import java.time.LocalDate;

import com.tekup.car_rental.model.Car;
import com.tekup.car_rental.model.CarState;

import lombok.Data;

@Data
public class GuestCarDto {
    private String brand;

    private String model;

    private LocalDate manufactureYear;

    private Double pricePerNight;

    private CarState state;

    private String image;

    public static GuestCarDto mappper(Car car) {
        GuestCarDto guestCarDto = new GuestCarDto();
        guestCarDto.brand = car.getBrand();
        guestCarDto.model = car.getModel();
        guestCarDto.manufactureYear = car.getManufactureYear();
        guestCarDto.pricePerNight = car.getPricePerNight();
        guestCarDto.image = car.getImage();
        guestCarDto.state = car.getState();
        return guestCarDto;
    }
}
