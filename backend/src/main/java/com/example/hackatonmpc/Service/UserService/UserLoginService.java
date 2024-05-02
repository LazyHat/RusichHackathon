package com.example.hackatonmpc.Service.UserService;

import com.example.hackatonmpc.DTO.LoginDTO;
import com.example.hackatonmpc.Entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    private final UserServiceDB userServiceDB;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DefaultTokenUserService tokenUserService;
    public UserLoginService(UserServiceDB userServiceDB, BCryptPasswordEncoder bCryptPasswordEncoder, DefaultTokenUserService tokenUserService) {
        this.userServiceDB = userServiceDB;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenUserService = tokenUserService;
    }

    public ResponseEntity<?> checkUser(LoginDTO LoginDTO) {
        try {
            UserEntity user = userServiceDB.getAdminByLogin(LoginDTO.getLogin());
            if (bCryptPasswordEncoder.matches(LoginDTO.getPassword(), user.getPassword())) {
                String token =tokenUserService.generateToken(user.getId());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(401).body("Wrong login or password");
            }
        } catch (NullPointerException e) {
            return ResponseEntity.status(401).body("Wrong login or password");
        }
    }
}
