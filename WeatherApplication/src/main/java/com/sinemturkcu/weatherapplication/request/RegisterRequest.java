package com.sinemturkcu.weatherapplication.request;

import com.sinemturkcu.weatherapplication.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;

    /*
    "firstname":"a",
    "lastname":"a",
    "email":"a",
    "password":"a",
    "role":"USER"
     */

}

