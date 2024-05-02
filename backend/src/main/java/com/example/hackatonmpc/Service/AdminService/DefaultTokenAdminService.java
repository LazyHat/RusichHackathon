package com.example.hackatonmpc.Service.AdminService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.hackatonmpc.Entity.AdminEntity;
import com.example.hackatonmpc.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
@Service
public class DefaultTokenAdminService {
    private final AdminRepository adminRepository;

    @Value("${auth.jwt.secret}")
    private String secretKey;

    public DefaultTokenAdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public String generateToken(Long id) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant now = Instant.now();
        Instant exp = now.plus(120, ChronoUnit.MINUTES);

        return JWT.create()
                .withSubject(id.toString())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .sign(algorithm);
    }


    public Boolean checkToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        System.out.println(token);
        System.out.println(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            if (!decodedJWT.getIssuer().equals("userService")) {
                System.out.println("Issuer is incorrect");
                return false;
            }
            if (!checkId(Long.valueOf(decodedJWT.getSubject()))) {
                System.out.println("Id is incorrect");
                return false;
            }
        } catch (JWTVerificationException e) {
            System.out.println("Token is invalid: " + e.getMessage());
            return false;
        }

        return true;
    }
    public Long getIdFromToken(String checkedToken) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(checkedToken);
        return Long.valueOf(decodedJWT.getSubject());
    }
    private Boolean checkId(Long id) {
        System.out.println(id.toString());
        Optional<AdminEntity> optional = adminRepository.findById(id);
        if(optional.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
}