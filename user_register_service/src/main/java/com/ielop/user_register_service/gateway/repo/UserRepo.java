package com.ielop.user_register_service.gateway.repo;

import com.ielop.user_register_service.gateway.json.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepo extends CrudRepository<User, UUID> {
}
