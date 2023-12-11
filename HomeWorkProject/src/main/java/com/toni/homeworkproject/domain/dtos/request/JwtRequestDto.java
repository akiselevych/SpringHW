package com.toni.homeworkproject.domain.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequestDto {
    private String login;
    private String password;
}
