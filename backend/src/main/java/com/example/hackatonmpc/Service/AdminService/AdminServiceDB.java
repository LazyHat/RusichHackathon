package com.example.hackatonmpc.Service.AdminService;

import com.example.hackatonmpc.Entity.AdminEntity;
import com.example.hackatonmpc.Repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceDB {

    private final AdminRepository adminRepository;

    public AdminServiceDB(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminEntity getAdminByLogin(String login) throws NullPointerException {
        Optional<AdminEntity> optional = adminRepository.findByLogin(login);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NullPointerException();
        }
    }


}

