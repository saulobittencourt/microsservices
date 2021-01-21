package com.ielop.auth_service.payload;

import com.ielop.auth_service.messaging.enums.UserEventTypes;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEvent {
    private String id;
    private String username;
    private String email;
    private String displayName;
    private String profilePictureUrl;
    private String oldProfilePicUrl;
    private UserEventTypes eventType;
}
