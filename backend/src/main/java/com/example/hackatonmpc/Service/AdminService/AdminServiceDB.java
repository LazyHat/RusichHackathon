package com.example.hackatonmpc.Service.AdminService;


import com.example.hackatonmpc.Entity.AdminEntity;
import com.example.hackatonmpc.Entity.UserEntity;
import com.example.hackatonmpc.Repository.AdminRepository;
import com.example.hackatonmpc.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceDB {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AdminServiceDB(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public AdminEntity getAdminByLogin(String login) throws NullPointerException {
        Optional<AdminEntity> optional = adminRepository.findByLogin(login);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NullPointerException();
        }
    }
    public ResponseEntity<?> adminRegister(AdminEntity adminEntity) {
        adminRepository.save(adminEntity);
        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<?> userRegister(UserEntity userEntity){
        userRepository.save(userEntity);
        return ResponseEntity.ok("Success");
    }

}

