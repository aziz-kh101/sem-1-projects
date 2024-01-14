package com.tekup.car_rental.service;

import java.util.List;
import java.util.Optional;

import com.tekup.car_rental.model.Admin;

public interface IAdminService {
    public Admin addAdmin(Admin admin);

    public Admin updateAdmin(Long id, Admin admin);

    public void deleteAdmin(Admin admin);

    public List<Admin> getAllAdmins();

    public Optional<Admin> getAdminById(Long id);
}
