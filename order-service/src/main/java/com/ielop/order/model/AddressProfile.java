package com.ielop.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressProfile {
    private String id;
    private String country;
    private String city;
    private String zipCode;
    private String streetName;
    private Long buildingNumber;
    private Long defaultAddress;
}
