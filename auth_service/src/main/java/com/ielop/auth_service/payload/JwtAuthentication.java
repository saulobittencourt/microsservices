package com.ielop.auth_service.payload;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtAuthentication {

    @NonNull
    private String accessToken;
    private String tokenType = "Bearer";
}
