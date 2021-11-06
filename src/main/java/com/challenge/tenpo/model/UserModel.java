package com.challenge.tenpo.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserModel {

    @NotBlank(message = "email is required")
    private String email;

    @NotNull(message = "password is required")
    private char[] password;

    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "userName is required")
    private String userName;

}
