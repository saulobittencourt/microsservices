package com.ielop.user_register_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ielop.user_register_service.gateway.json.User;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserListener {
    private final UserService userService;

    @KafkaListener(topics = "requesttopic2")
    @SendTo
    public String listener(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);

        UUID uuid = userService.salvar(user);
        user.setId(uuid);

        return mapper.writeValueAsString(user);
    }
}
