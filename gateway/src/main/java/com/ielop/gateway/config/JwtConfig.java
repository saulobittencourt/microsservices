package com.ielop.gateway.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class JwtConfig {
    private String Uri = "/auth/**";

    private String header = "Authorization";

    private String prefix = "Bearer ";

    private int expiration = 24*60*60;

    private String secret = "5f9f7765e428e9500296fead15b93435";
}
