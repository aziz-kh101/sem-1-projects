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

import com.tekup.car_rental.dto.ClientDto;
import com.tekup.car_rental.model.Client;
import com.tekup.car_rental.service.IClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/administration/clients")
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService clientService;

    @GetMapping({ "", "/" })
    public String clients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "admin/list-clients";
    }

    @GetMapping({ "/add", "/add/" })
    public String addForm(Model model) {
        model.addAttribute("clientDto", new ClientDto());
        return "admin/add-client";
    }

    @PostMapping({ "/add", "/add/" })
    public String addForm(@Valid @ModelAttribute("clientDto") ClientDto clientDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/add-client";
        }
        Client client = new Client();
        client.setCin(clientDto.getCin());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());

        try {
            clientService.addClient(client);
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("cin", "error.cin", "cin already exist");
            return "admin/add-client";
        }
        return "redirect:/administration/clients";
    }

    @GetMapping({ "/{id}/delete", "/{id}/delete" })
    public String confirmDelete(@PathVariable Long id, Model model) {
        Optional<Client> optional = clientService.getClientById(id);
        if (!optional.isPresent())
            return "redirect:/administration/clients";

        model.addAttribute("id", id);
        return "admin/delete-client";
    }

    @PostMapping({ "/{id}/delete", "/{id}/delete" })
    public String delete(@PathVariable Long id) {
        Optional<Client> optional = clientService.getClientById(id);
        if (!optional.isPresent())
            return "redirect:/administration/clients";

        clientService.deleteClient(optional.get());
        return "redirect:/administration/clients";
    }

    @GetMapping({ "/{id}/update", "/{id}/update/" })
    public String updateForm(Model model, @PathVariable("id") Long id) {
        Optional<Client> optional = clientService.getClientById(id);
        if (!optional.isPresent())
            return "redirect:/administration/clients";

        ClientDto clientDto = new ClientDto();
        clientDto.setCin(optional.get().getCin());
        clientDto.setFirstName(optional.get().getFirstName());
        clientDto.setLastName(optional.get().getLastName());
        model.addAttribute("clientDto", clientDto);
        return "admin/update-client";
    }

    @PostMapping({ "/{id}/update", "/{id}/update/" })
    public String update(@Valid @ModelAttribute("clientDto") ClientDto clientDto, @PathVariable("id") Long id,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/update-client";
        }
        Client client = new Client();
        client.setCin(clientDto.getCin());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());

        try {
            clientService.updateClient(id, client);
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("cin", "error.cin", "cin already exist");
            return "admin/update-client";
        }
        return "redirect:/administration/clients";
    }
}
