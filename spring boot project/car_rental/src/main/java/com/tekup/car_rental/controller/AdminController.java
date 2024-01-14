package com.tekup.car_rental.controller;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tekup.car_rental.dto.AdminAddDto;
import com.tekup.car_rental.dto.AdminUpdateDto;
import com.tekup.car_rental.model.Admin;
import com.tekup.car_rental.service.IAdminService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/administration/admins")
@PreAuthorize("hasRole('SUPER_ADMIN')")
@RequiredArgsConstructor
public class AdminController {
    private final IAdminService adminService;

    @GetMapping({ "", "/" })
    public String admins(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        return "admin/list-admins";
    }

    @GetMapping({ "/{id}/update", "/{id}/update/" })
    public String updateForm(Model model, @PathVariable("id") Long id) {
        Optional<Admin> optional = adminService.getAdminById(id);
        if (!optional.isPresent()) {
            return "redirect:/administration/admins";
        }
        AdminUpdateDto adminDto = new AdminUpdateDto();
        adminDto.setEmail(optional.get().getEmail());
        adminDto.setFirstName(optional.get().getFirstName());
        adminDto.setLastName(optional.get().getLastName());
        model.addAttribute("id", id);
        model.addAttribute("adminDto", adminDto);
        return "admin/update-admin";
    }

    @PostMapping({ "/{id}/update", "/{id}/update/" })
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("adminDto") AdminUpdateDto adminDto,
            BindingResult bindingResult) {
        System.out.println(adminDto);
        if (bindingResult.hasErrors()) {
            return "admin/update-admin";
        }
        Optional<Admin> optional = adminService.getAdminById(id);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException(id + " not exist");
        }
        Admin admin = optional.get();
        admin.setEmail(adminDto.getEmail());
        admin.setFirstName(adminDto.getLastName());
        admin.setLastName(adminDto.getLastName());
        if (adminDto.getPassword() != null && !adminDto.getPassword().isBlank()
                && !(adminDto.getPassword().length() < 6))
            admin.setPassword(adminDto.getPassword());

        try {
            adminService.updateAdmin(id, admin);
        } catch (DataIntegrityViolationException ex) {
            bindingResult.rejectValue("email", "error.email", "email already exist");
            return "admin/add-admin";
        }
        return "redirect:/administration/admins";
    }

    @GetMapping({ "/add", "/add/" })
    public String addForm(Model model) {
        AdminAddDto adminDto = new AdminAddDto();
        model.addAttribute("adminDto", adminDto);
        return "admin/add-admin";
    }

    @PostMapping({ "/add", "/add/" })
    public String add(@Valid @ModelAttribute("adminDto") AdminAddDto adminDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/add-admin";
        }
        Admin admin = new Admin();
        admin.setEmail(adminDto.getEmail());
        admin.setFirstName(adminDto.getLastName());
        admin.setLastName(adminDto.getLastName());
        admin.setPassword(adminDto.getPassword());
        try {
            adminService.addAdmin(admin);
        } catch (DataIntegrityViolationException ex) {
            bindingResult.rejectValue("email", "error.email", "email already exist");
            return "admin/add-admin";
        }
        return "redirect:/administration/admins";
    }

    @GetMapping({ "/{id}/delete", "/{id}/delete/" })
    public String deleteConfirm(Model model, @PathVariable("id") Long id) {
        Optional<Admin> optional = adminService.getAdminById(id);
        if (!optional.isPresent()) {
            return "redirect:/administration/admins";
        }
        model.addAttribute("id", id);
        model.addAttribute("adminEmail", optional.get().getEmail());
        return "admin/delete-admin";
    }

    @PostMapping({ "/{id}/delete", "/{id}/delete/" })
    public String delete(@PathVariable("id") Long id) {
        Optional<Admin> optional = adminService.getAdminById(id);
        optional.ifPresent(adminService::deleteAdmin);
        return "redirect:/administration/admins";
    }
}
