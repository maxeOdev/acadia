package com.hb.acadia.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {


    protected String uuid;

    protected String name;

    protected String firstName;

    protected String email;

    protected AddressDTO address;

    public UserDTO() {
    }

}
