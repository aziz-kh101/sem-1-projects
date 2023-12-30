
package com.tekup.car_rental.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.tekup.car_rental.dto.CarDto;
import com.tekup.car_rental.model.Car;
import com.tekup.car_rental.model.CarState;
import com.tekup.car_rental.model.RentState;
import com.tekup.car_rental.model.User;
import com.tekup.car_rental.service.ICarService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/administration/cars")
@PreAuthorize("hasRole('SUPER_ADMIN')")
@RequiredArgsConstructor
public class CarController {
    private final ICarService carService;
    private final String UPLOAD_FOLDER_NAME = "uploads";
    private final String STATIC_FOLDER_PATH = "src/main/resources/static/";
    private final Path UPLOAD_PATH = Paths.get(STATIC_FOLDER_PATH + UPLOAD_FOLDER_NAME);

    @GetMapping({ "", "/" })
    public String admins(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "admin/list-cars";
    }

    @GetMapping({ "/add", "/add/" })
    public String addForm(Model model) {
        CarDto carDto = new CarDto();
        model.addAttribute("carDto", carDto);
        return "admin/add-car";
    }

    @PostMapping(path = { "/add", "/add/" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String add(@Valid @ModelAttribute("carDto") CarDto carDto, BindingResult bindingResult,
            Authentication authentication) {
        if (carDto.getImage() != null && carDto.getImage().isEmpty()) {
            bindingResult.rejectValue("image", "error.image", "you need to select image");
        }
        if (bindingResult.hasErrors()) {
            return "admin/add-car";
        }
        User user = (User) authentication.getPrincipal();

        Car car = new Car();
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setState(carDto.getState());
        car.setManufactureYear(carDto.getManufactureYear());
        car.setPricePerNight(carDto.getPricePerNight());
        try {

            car.setImage(getImageName(carDto.getImage(), user.getId()));
        } catch (IOException ex) {

        }

        carService.addCar(car);
        return "redirect:/administration/cars";
    }

    @GetMapping({ "/{id}/delete", "/{id}/delete/" })
    public String deleteConfirm(Model model, @PathVariable("id") Long id) {
        Optional<Car> optional = carService.getCarById(id);
        if (!optional.isPresent()) {
            return "redirect:/administration/cars";
        }
        model.addAttribute("car", optional.get());
        return "admin/delete-car";
    }

    @PostMapping({ "/{id}/delete", "/{id}/delete/" })
    public String delete(@PathVariable("id") Long id) {
        Optional<Car> optional = carService.getCarById(id);
        optional.ifPresent(this::deleteCar);
        return "redirect:/administration/cars";
    }

    @GetMapping({ "/{id}/update", "/{id}/update/" })
    public String updateForm(Model model, @PathVariable("id") Long id) {
        Optional<Car> optional = carService.getCarById(id);
        if (!optional.isPresent()) {
            return "redirect:/administration/cars";
        }
        CarDto carDto = new CarDto();
        carDto.setBrand(optional.get().getBrand());
        carDto.setModel(optional.get().getModel());
        carDto.setState(optional.get().getState());
        carDto.setManufactureYear(optional.get().getManufactureYear());
        carDto.setPricePerNight(optional.get().getPricePerNight());
        model.addAttribute("carDto", carDto);
        System.out.println(optional.get().getManufactureYear());
        return "admin/update-car";
    }

    @PostMapping({ "/{id}/update", "/{id}/update/" })
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("carDto") CarDto carDto,
            BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "admin/update-car";
        }

        User user = (User) authentication.getPrincipal();

        Optional<Car> optional = carService.getCarById(id);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException(id + " not exist");
        }

        if (carDto.getState() != CarState.RENT
                && optional.get().getRents().stream().anyMatch(r -> r.getState() == RentState.ONGOING)) {
            bindingResult.rejectValue("state", "error.state", "there is rent ongoing");
            return "admin/update-car";
        }

        Car car = optional.get();
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setState(carDto.getState());
        car.setManufactureYear(carDto.getManufactureYear());
        car.setPricePerNight(carDto.getPricePerNight());

        String oldImage = null;

        if (carDto.getImage() != null && !carDto.getImage().isEmpty()) {
            try {
                oldImage = car.getImage();
                car.setImage(getImageName(carDto.getImage(), user.getId()));
            } catch (IOException ex) {
                oldImage = null;
            }
        }

        carService.updateCar(id, car);
        try {
            deleteImage(oldImage);
        } catch (IOException e) {
            System.out.println(e);
        }
        return "redirect:/administration/cars";
    }

    private String getImageName(MultipartFile multipartFile, Long userId) throws IOException {
        String imageName = "img_" + userId + "_"
                + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) + "_"
                + Math.round(Math.random() * 1000)
                + "."
                + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_PATH + "/" + imageName));
        return "/" + UPLOAD_FOLDER_NAME + "/" + imageName;
    }

    private void deleteCar(Car car) {
        carService.deleteCar(car);
        try {
            deleteImage(car.getImage());
        } catch (IOException ex) {

        }
    }

    private void deleteImage(String image) throws IOException {
        if (image != null) {
            Path path = Paths.get(STATIC_FOLDER_PATH, ".", image);
            Files.deleteIfExists(path);
        }
    }

}