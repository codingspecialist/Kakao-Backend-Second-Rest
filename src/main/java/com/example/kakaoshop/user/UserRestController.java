package com.example.kakaoshop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final UserJPARepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO joinDTO) {
        User user = User.builder()
                .email(joinDTO.getEmail())
                .password(passwordEncoder.encode(joinDTO.getPassword()))
                .username(joinDTO.getUsername())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        try {
            userRepository.save(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("fail");
        }

        return ResponseEntity.ok("ok");
    }
}
