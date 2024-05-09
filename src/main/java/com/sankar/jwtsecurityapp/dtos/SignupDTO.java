package com.sankar.jwtsecurityapp.dtos;

import lombok.Data;

@Data
public class SignupDTO {

    private String name;
    private String email;
    private String password;

}
