package com.example.hackatonmpc.Repository;

import com.example.hackatonmpc.Entity.AdminEntity;
import com.example.hackatonmpc.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
}
