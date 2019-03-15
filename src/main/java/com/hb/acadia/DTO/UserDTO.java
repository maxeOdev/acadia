package com.hb.acadia.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UserDTO {

    @NotNull
    protected String uuid;
    @NotNull
    protected String name;
    @NotNull
    protected String firstName;
    @NotNull
    protected String email;

    protected AddressDTO address;

    public UserDTO() {
    }

}
