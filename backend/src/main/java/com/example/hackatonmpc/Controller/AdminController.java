package com.example.hackatonmpc.Controller;

import com.example.hackatonmpc.DTO.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(LoginDTO loginDTO) {

    }
}
