package com.ielop.auth_service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Api {
    private Boolean success;
    private String message;
}
