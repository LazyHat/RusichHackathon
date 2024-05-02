package com.example.hackatonmpc.Controller;
import com.example.hackatonmpc.DTO.LoginDTO;
import com.example.hackatonmpc.Service.UserService.UserLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserLoginService userLoginService;
    public UserController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(LoginDTO loginDTO) {
        return userLoginService.checkUser(loginDTO);
    }

}
