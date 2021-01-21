package com.ielop.auth_service.repo;

import com.ielop.auth_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findUserByUsername(String username);
    List<User> findUserByUsernameIn(List<String> usernames);
    Boolean existsUserByUsername(String username);
    Boolean existsUserByEmail(String email);
}
