package com.hb.acadia.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDTO {


    private int number;

    private String road;

    private String roadType;

    private String cp;

    private String city;

    private String country;

    public AddressDTO() {
    }
}
