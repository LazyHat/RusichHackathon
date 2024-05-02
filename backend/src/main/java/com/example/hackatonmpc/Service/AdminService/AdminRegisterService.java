package com.example.hackatonmpc.Service.AdminService;

import com.example.hackatonmpc.DTO.RegisterAdminDTO;

import com.example.hackatonmpc.DTO.RegisterDTO;
import com.example.hackatonmpc.Entity.AdminEntity;
import com.example.hackatonmpc.Entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminRegisterService {
    private final AdminServiceDB adminServiceDB;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DefaultTokenAdminService defaultTokenAdminService;

    public AdminRegisterService(AdminServiceDB adminServiceDB, BCryptPasswordEncoder bCryptPasswordEncoder, DefaultTokenAdminService defaultTokenAdminService) {
        this.adminServiceDB = adminServiceDB;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.defaultTokenAdminService = defaultTokenAdminService;
    }

    public ResponseEntity<?> adminRegister (RegisterAdminDTO registerAdminDTO){
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setName(registerAdminDTO.getName());
        adminEntity.setLogin(registerAdminDTO.getLogin());
        adminEntity.setPassword(bCryptPasswordEncoder.encode(registerAdminDTO.getPassword()));
        return adminServiceDB.adminRegister(adminEntity);
    }

    public ResponseEntity<?> userRegister (RegisterDTO registerDTO){
        if(registerDTO.getToken()==null){
            return ResponseEntity.badRequest().build();
        }
        String token = registerDTO.getToken();
        if(!defaultTokenAdminService.checkToken(token)){
            return ResponseEntity.badRequest().build();
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(registerDTO.getName());
        userEntity.setLogin(registerDTO.getLogin());
        userEntity.setRegistrationDate(LocalDateTime.now());
        userEntity.setPassword(bCryptPasswordEncoder.encode(registerDTO.getPassword()));
        return adminServiceDB.userRegister(userEntity);
    }
}
