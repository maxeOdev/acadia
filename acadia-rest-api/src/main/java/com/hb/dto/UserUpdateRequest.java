package com.hb.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserUpdateRequest {

    @NotNull
    @NotEmpty
    protected String name;
    @NotNull
    @NotEmpty
    protected String firstName;
    @NotNull
    @Email
    @NotEmpty(message = "L'adresse e-mail ne peut pas être vide")
    protected String email;

    protected boolean isActif;

    private int number;

    private String road;

    private String roadType;

    private String cp;

    private String city;

    private String country;



}
