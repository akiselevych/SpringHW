package com.toni.homeworkproject.domain.dtos.response;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDto {
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
}
