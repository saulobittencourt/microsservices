package com.ielop.auth_service.service;

import com.ielop.auth_service.exception.ServiceBusinessException;
import com.ielop.auth_service.messaging.UserEventSender;
import com.ielop.auth_service.model.User;
import com.ielop.auth_service.model.enums.Role;
import com.ielop.auth_service.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final UserEventSender userEventSender;

    public List<User> findAll(){
        List<User> users = userRepo.findAll();
        if (users.isEmpty()){
            throw new ServiceBusinessException("Not have user on the system");
        }
        return users;
    }

    public Optional<User> findByUsername(String username){
        Optional<User> user = userRepo.findUserByUsername(username);
        if (user.isEmpty()){
            throw new ServiceBusinessException("Username "+ user.get().getUsername() +" not exists");
        }
        return user;
    }

    public List<User> findByUsernameIn(List<String> usernames) {
        return userRepo.findUserByUsernameIn(usernames);
    }

    public User save(User user){
        if (userRepo.existsUserByUsername(user.getUsername())){
            throw new ServiceBusinessException("Username "+ user.getUsername() +" already exists");
        } else if (userRepo.existsUserByEmail(user.getEmail())){
            throw new ServiceBusinessException("E-mail "+ user.getEmail() +" already exists");
        }

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new ArrayList<>(){{
            add(Role.USER);
        }});

        User userSaved = userRepo.save(user);
        userEventSender.sendUserCreated(userSaved);

        return user;
    }

    public User updateProfilePicture(String uri, String userId){
        return userRepo.findById(userId)
                .map(user -> {
                    String oldProfilePic = user.getUserProfile().getProfilePictureUrl();
                    user.getUserProfile().setProfilePictureUrl(uri);
                    User updatedUser = userRepo.save(user);

                    userEventSender.sendUserUpdated(updatedUser, oldProfilePic);

                    return updatedUser;
                }).orElseThrow(() -> new ServiceBusinessException("User not found"));
    }
}
