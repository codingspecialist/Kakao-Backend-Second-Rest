package com.example.kakaoshop.user;

import lombok.*;
import java.util.Collections;

public class UserRequest {

    @Getter
    @Setter
    public static class JoinDTO {

        private String email;
        private String password;
        private String username;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .password(password)
                    .username(username)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }
    }
}
