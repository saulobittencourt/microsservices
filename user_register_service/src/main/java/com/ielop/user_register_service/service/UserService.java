package com.ielop.user_register_service.service;


import com.ielop.user_register_service.gateway.json.User;
import com.ielop.user_register_service.gateway.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public UUID salvar(User user) {
        user.setId(UUID.randomUUID());
        userRepo.save(user);

        return user.getId();
    }
}
