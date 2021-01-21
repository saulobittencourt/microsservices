package com.ielop.auth_service.config;

import com.ielop.auth_service.model.AuthUserDetails;
import com.ielop.auth_service.service.JwtTokenService;
import com.ielop.auth_service.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final JwtTokenService jwtTokenService;
    private final UserService userService;
    private String serviceUsername;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(jwtConfig.getHeader());

        if(header == null || !header.startsWith(jwtConfig.getPrefix())){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = header.replace(jwtConfig.getPrefix(),"");
        if(jwtTokenService.validateToken(token)) {

            Claims claims = jwtTokenService.getClaimsFromJWT(token);
            String username = claims.getSubject();

            UsernamePasswordAuthenticationToken auth = null;

            if(username.equals(serviceUsername)) { // If it is service account don't load user details
                List<String> authorities = (List<String>) claims.get("authorities");
                auth = new UsernamePasswordAuthenticationToken(username, null,
                        authorities
                                .stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList()));
            } else {
                auth = userService
                        .findByUsername(username)
                        .map(AuthUserDetails::new)
                        .map(userDetails -> {
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(
                                            userDetails, null, userDetails.getAuthorities());
                            authentication
                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                            return authentication;
                        })
                        .orElse(null);
            }

            SecurityContextHolder.getContext().setAuthentication(auth);

        } else {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
