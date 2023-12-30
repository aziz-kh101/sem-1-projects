package com.tekup.car_rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tekup.car_rental.model.Admin;
import com.tekup.car_rental.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Admin addAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {
        Admin updateAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " not found"));
        updateAdmin.setEmail(admin.getEmail());
        updateAdmin.setFirstName(admin.getFirstName());
        updateAdmin.setLastName(admin.getLastName());
        if (admin.getPassword() != null)
            updateAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(updateAdmin);
    }

    @Override
    public void deleteAdmin(Admin admin) {
        adminRepository.delete(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

}
