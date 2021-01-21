package com.ielop.auth_service.messaging;

import com.ielop.auth_service.messaging.enums.UserEventTypes;
import com.ielop.auth_service.model.User;
import com.ielop.auth_service.payload.UserEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEventSender {
    private final UserEventStream userEventStream;

    public void  sendUserCreated(User user){
        sendUserChangedEvent(convertTo(user, UserEventTypes.CREATED));
    }

    public void  sendUserUpdated(User user){
        sendUserChangedEvent(convertTo(user, UserEventTypes.UPDATED));
    }

    public void  sendUserUpdated(User user, String oldPicUrl){
        UserEvent userEvent = convertTo(user, UserEventTypes.UPDATED);
        userEvent.setOldProfilePicUrl(oldPicUrl);

        sendUserChangedEvent(userEvent);
    }

    private void sendUserChangedEvent(UserEvent payload) {
        Message<UserEvent> message = MessageBuilder.withPayload(payload).setHeader(KafkaHeaders.MESSAGE_KEY, payload.getId()).build();
        userEventStream.momentsUserChanged().send(message);
    }

    private UserEvent convertTo(User user, UserEventTypes eventType) {
        return UserEvent.builder()
                .eventType(eventType)
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .displayName(user.getUserProfile().getDisplayName())
                .profilePictureUrl(user.getUserProfile().getProfilePictureUrl())
                .build();
    }
}
