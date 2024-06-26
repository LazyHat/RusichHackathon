package com.example.hackatonmpc.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdminDTO {
    private String name;
    private String login;
    private String password;
    private String secretWord;
}
