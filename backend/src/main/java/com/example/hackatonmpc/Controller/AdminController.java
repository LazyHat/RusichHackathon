package com.example.hackatonmpc.Controller;

import com.example.hackatonmpc.DTO.LoginDTO;
import com.example.hackatonmpc.DTO.RegisterDTO;
import com.example.hackatonmpc.Service.AdminService.AdminLoginService;
import com.example.hackatonmpc.Service.AdminService.AdminRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    public final AdminLoginService adminLoginService;
    public final AdminRegisterService adminRegisterService;

    public AdminController(AdminLoginService adminLoginService, AdminRegisterService adminRegisterService) {
        this.adminLoginService = adminLoginService;
        this.adminRegisterService = adminRegisterService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(LoginDTO loginDTO) {
        return adminLoginService.checkAdmin(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> adminRegister(RegisterDTO registerDTO){
        return adminRegisterService.adminRegister(registerDTO);
    }
}
