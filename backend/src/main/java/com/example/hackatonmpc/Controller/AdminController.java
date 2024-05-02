package com.example.hackatonmpc.Controller;

import com.example.hackatonmpc.DTO.LoginDTO;
import com.example.hackatonmpc.Service.AdminService.AdminLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminLoginService adminLoginService;

    public AdminController(AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(LoginDTO loginDTO) {
        return adminLoginService.checkAdmin(loginDTO);
    }
}
