package com.ielop.auth_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private String id;
    private String country;
    private String city;
    private String zipCode;
    private String streetName;
    private Long buildingNumber;
}
