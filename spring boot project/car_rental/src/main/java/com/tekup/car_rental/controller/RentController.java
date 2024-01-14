package com.tekup.car_rental.controller;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tekup.car_rental.dto.RentDto;
import com.tekup.car_rental.model.Car;
import com.tekup.car_rental.model.CarState;
import com.tekup.car_rental.model.Client;
import com.tekup.car_rental.model.Rent;
import com.tekup.car_rental.model.RentState;
import com.tekup.car_rental.model.User;
import com.tekup.car_rental.service.ICarService;
import com.tekup.car_rental.service.IClientService;
import com.tekup.car_rental.service.IRentService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/administration/rents")
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
@RequiredArgsConstructor
public class RentController {
    private final IRentService rentService;
    private final ICarService carService;
    private final IClientService clientService;

    @GetMapping({ "", "/" })
    public String clients(Model model) {
        model.addAttribute("rents", rentService.getAllRents());
        return "admin/list-rents";
    }

    @GetMapping({ "/add", "/add/" })

    public String addForm(Model model, HttpSession session) {
        RentDto rentDto = new RentDto();
        rentDto.setState(RentState.ONGOING);
        session.setAttribute("listCars", carService.getAllCars());
        session.setAttribute("listClients", clientService.getAllClients());
        model.addAttribute("rentDto", rentDto);
        return "admin/add-rent";
    }

    @PostMapping({ "/add", "/add/" })
    public String addForm(@Valid @ModelAttribute("rentDto") RentDto rentDto,
            BindingResult bindingResult,
            Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "admin/add-rent";
        }
        Car selectedCar = carService.getCarById(rentDto.getCarId()).get();
        Client selectedClient = clientService.getClientById(rentDto.getClientId()).get();
        if (rentDto.getState() == RentState.ONGOING && selectedCar.getState() != CarState.AVAILABLE) {
            bindingResult.rejectValue("carId", "error.carId", "car not available");
            return "admin/add-rent";
        }
        if (!rentDto.getStartDate().isBefore(rentDto.getEndDate())) {
            bindingResult.rejectValue("startDate", "error.startDate", "startDate should be before end date");
            return "admin/add-rent";
        }
        Rent rent = new Rent();
        rent.setCar(selectedCar);
        rent.setClient(selectedClient);
        rent.setStartDate(rentDto.getStartDate());
        rent.setEndDate(rentDto.getEndDate());
        rent.setState(rentDto.getState());
        rent.setAddedBy((User) authentication.getPrincipal());

        rentService.addRent(rent);

        return "redirect:/administration/rents";
    }

    @GetMapping({ "/{id}/delete", "/{id}/delete" })
    public String confirmDelete(@PathVariable Long id, Model model) {
        Optional<Rent> optional = rentService.getRentById(id);
        if (!optional.isPresent())
            return "redirect:/administration/rents";
        model.addAttribute("id", id);
        return "admin/delete-rent";
    }

    @PostMapping({ "/{id}/delete", "/{id}/delete" })
    public String delete(@PathVariable Long id) {
        Optional<Rent> optional = rentService.getRentById(id);
        if (!optional.isPresent())
            return "redirect:/administration/rents";

        rentService.deleteRent(optional.get());
        return "redirect:/administration/rents";
    }

    @GetMapping({ "/{id}/update", "/{id}/update/" })
    public String updateForm(Model model, @PathVariable("id") Long id, HttpSession session) {
        Optional<Rent> optional = rentService.getRentById(id);
        if (!optional.isPresent())
            return "redirect:/administration/rents";

        RentDto rentDto = new RentDto();
        rentDto.setCarId(optional.get().getCar().getId());
        rentDto.setClientId(optional.get().getClient().getId());
        rentDto.setStartDate(optional.get().getStartDate());
        rentDto.setState(optional.get().getState());
        rentDto.setEndDate(optional.get().getEndDate());

        session.setAttribute("listCars", carService.getAllCars());
        session.setAttribute("listClients", clientService.getAllClients());
        model.addAttribute("rentDto", rentDto);
        return "admin/update-rent";
    }

    @PostMapping({ "/{id}/update", "/{id}/update/" })
    public String update(@Valid @ModelAttribute("rentDto") RentDto rentDto, @PathVariable("id") Long id,
            BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "admin/update-rent";
        }
        Car selectedCar = carService.getCarById(rentDto.getCarId()).get();
        Client selectedClient = clientService.getClientById(rentDto.getClientId()).get();

        if (rentDto.getState() == RentState.ONGOING && selectedCar.getState() != CarState.AVAILABLE) {
            bindingResult.rejectValue("carId", "error.carId", "car not available");
            return "admin/update-rent";
        }
        if (!rentDto.getStartDate().isBefore(rentDto.getEndDate())) {
            bindingResult.rejectValue("startDate", "error.startDate", "startDate should be before end date");
            return "admin/update-rent";
        }
        Rent rent = new Rent();
        rent.setCar(selectedCar);
        rent.setClient(selectedClient);
        rent.setStartDate(rentDto.getStartDate());
        rent.setEndDate(rentDto.getEndDate());
        rent.setState(rentDto.getState());
        rent.setAddedBy((User) authentication.getPrincipal());

        rentService.updateRent(id, rent);
        return "redirect:/administration/rents";
    }
}