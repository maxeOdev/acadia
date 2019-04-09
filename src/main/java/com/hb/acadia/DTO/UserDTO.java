package com.hb.acadia.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@ToString
public class UserDTO {

    @NotNull
    protected String uuid;
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

    protected AddressDTO address;

    public UserDTO() {
    }

}
