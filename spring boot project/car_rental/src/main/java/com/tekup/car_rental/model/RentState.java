package com.tekup.car_rental.model;

import lombok.Getter;

@Getter
public enum RentState {
    ONGOING("ongoing"),
    DONE("done");

    private final String displayValue;

    private RentState(String displayValue) {
        this.displayValue = displayValue;
    }
}