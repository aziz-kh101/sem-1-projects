package com.tekup.car_rental.model;

import lombok.Getter;

@Getter
public enum CarState {
    AVAILABLE("Available"),
    RENT("Rent"),
    REPAIR("Repair");

    private final String displayValue;

    private CarState(String displayValue) {
        this.displayValue = displayValue;
    }
}
