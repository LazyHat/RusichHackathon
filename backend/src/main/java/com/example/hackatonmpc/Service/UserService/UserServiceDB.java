package com.example.hackatonmpc.Service.UserService;


import com.example.hackatonmpc.Entity.UserEntity;
import com.example.hackatonmpc.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceDB {
    private final UserRepository userRepository;
    public UserServiceDB(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getAdminByLogin(String login) throws NullPointerException {
        Optional<UserEntity> optional = userRepository.findByLogin(login);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NullPointerException();
        }
    }
}
