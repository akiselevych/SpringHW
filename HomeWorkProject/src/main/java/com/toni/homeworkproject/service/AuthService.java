package com.toni.homeworkproject.service;


import com.toni.homeworkproject.auth.JwtProvider;
import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.dtos.request.JwtRequestDto;
import com.toni.homeworkproject.domain.dtos.response.JwtResponseDto;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public JwtResponseDto login(@NonNull JwtRequestDto request) throws AuthException {
        final Customer customer = customerService.findById(6L).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
        if (customer.getPassword().equals(request.getPassword())){
            String access = jwtProvider.generateAccessToken(customer);
            String refresh = jwtProvider.generateRefreshToken(customer);

            return new JwtResponseDto(access,refresh);
        } else {
            throw new AuthException("Password is incorrect");
        }
    }

    public JwtResponseDto refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)){
            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String email = claims.getSubject();
            Customer customer = customerService.findCustomerByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Customer not found"));
            String access = jwtProvider.generateAccessToken(customer);
            String refresh = jwtProvider.generateRefreshToken(customer);

            return new JwtResponseDto(access,refresh);
        } else {
            throw  new AuthException("Refresh token is not valid");
        }
    }
}
