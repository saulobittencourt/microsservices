package com.ielop.auth_service.service;

import com.ielop.auth_service.model.User;
import com.ielop.auth_service.model.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userDetailsService")
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.get().getRoles());

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities;
            }

            @Override
            public String getPassword() {
                return user.get().getPassword();
            }

            @Override
            public String getUsername() {
                return user.get().getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return user.get().getActive();
            }

            @Override
            public boolean isAccountNonLocked() {
                return user.get().getActive();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return user.get().getActive();
            }

            @Override
            public boolean isEnabled() {
                return user.get().getActive();
            }
        };

        return userDetails;
    }

    private List<GrantedAuthority> buildUserAuthority(List<Role> userRoles) {

        List<GrantedAuthority> setAuths = new ArrayList<>();

        // Build user's authorities
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getDescricao()));
        }

        return setAuths;
    }
}
