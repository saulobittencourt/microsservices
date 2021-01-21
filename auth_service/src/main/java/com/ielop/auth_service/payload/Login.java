package com.ielop.auth_service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
