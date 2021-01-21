package com.ielop.auth_service.util;

import com.ielop.auth_service.model.User;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Tools {

    public static void createDir(User user) {
        ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}").buildAndExpand(user.getUsername()).toUri();
    }
}
