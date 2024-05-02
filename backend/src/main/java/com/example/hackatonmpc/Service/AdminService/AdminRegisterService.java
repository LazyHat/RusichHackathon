package com.example.hackatonmpc.Service.AdminService;

import com.example.hackatonmpc.DTO.RegisterDTO;
import com.example.hackatonmpc.Entity.AdminEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminRegisterService {
    private final AdminServiceDB adminServiceDB;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminRegisterService(AdminServiceDB adminServiceDB, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminServiceDB = adminServiceDB;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<?> adminRegister (RegisterDTO registerDTO){
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setName(registerDTO.getName());
        adminEntity.setLogin(registerDTO.getLogin());
        adminEntity.setPassword(bCryptPasswordEncoder.encode(registerDTO.getPassword()));
        return adminServiceDB.adminRegister(adminEntity);
    }
}
