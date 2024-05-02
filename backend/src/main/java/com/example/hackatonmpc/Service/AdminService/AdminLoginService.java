package com.example.hackatonmpc.Service.AdminService;

import com.example.hackatonmpc.DTO.LoginDTO;
import com.example.hackatonmpc.Entity.AdminEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {
    private final AdminServiceDB adminServiceDB;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DefaultTokenAdminService tokenAdminService;
    public AdminLoginService(AdminServiceDB adminServiceDB, BCryptPasswordEncoder bCryptPasswordEncoder, DefaultTokenAdminService tokenAdminService) {
        this.adminServiceDB = adminServiceDB;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenAdminService = tokenAdminService;
    }

    public ResponseEntity<?> checkAdmin(LoginDTO LoginDTO) {
        try {
            AdminEntity admin = adminServiceDB.getAdminByLogin(LoginDTO.getLogin());
            if (bCryptPasswordEncoder.matches(LoginDTO.getPassword(), admin.getPassword())) {
                String token =tokenAdminService.generateToken(admin.getId());

                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(401).body("Wrong login or password");
            }
        } catch (NullPointerException e) {
            return ResponseEntity.status(401).body("Wrong login or password");
        }
    }

}
