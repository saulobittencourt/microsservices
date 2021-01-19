package com.ielop.user_register.gateway.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ielop.user_register.gateway.json.User;
import com.ielop.user_register.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> salvar (@RequestBody User user) throws InterruptedException, ExecutionException, JsonProcessingException {
        return ResponseEntity.ok(userService.salvar(user));
    }
}
