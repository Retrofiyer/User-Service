package com.user_service.entities;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserMessage {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
    private String token;
}
