package com.tekup.car_rental.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tekup.car_rental.dto.GuestCarDto;
import com.tekup.car_rental.model.SuperAdmin;
import com.tekup.car_rental.service.ICarService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GuestController {
    private final ICarService carService;

    @GetMapping
    public String home() {
        return "guest/home_page";
    }

    @GetMapping({ "/list-cars", "/list-cars/" })
    public String listCars(Model model) {
        List<GuestCarDto> cars = carService.getAllCars().stream().map(GuestCarDto::mappper).toList();
        model.addAttribute("cars", cars);
        return "guest/list_cars";
    }

    @GetMapping({ "/administration", "/administration/" })
    public String redirect(Authentication authentication) {
        if (authentication.getPrincipal() instanceof SuperAdmin) {
            return "redirect:/administration/admins";
        } else {
            return "redirect:/administration/rents";
        }
    }

}
