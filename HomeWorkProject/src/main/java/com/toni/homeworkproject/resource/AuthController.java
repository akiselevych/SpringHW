package com.toni.homeworkproject.resource;

import com.toni.homeworkproject.domain.dtos.request.JwtRequestDto;
import com.toni.homeworkproject.domain.dtos.response.JwtResponseDto;
import com.toni.homeworkproject.service.AuthService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequestDto request){
        try {
           JwtResponseDto jwtResponseDto = authService.login(request);
           log.info("User logged in");
            return ResponseEntity.ok(jwtResponseDto);
        } catch (AuthException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody String refreshToken){
        try {
            log.info("User refreshed token");
            return ResponseEntity.ok(authService.refresh(refreshToken));
        } catch (AuthException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
