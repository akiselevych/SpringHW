package com.toni.homeworkproject.facade.role;


import com.toni.homeworkproject.domain.Role;
import com.toni.homeworkproject.domain.dtos.response.RoleResponseDto;
import com.toni.homeworkproject.facade.DtoMapperFacade;

import java.util.Set;

public class RoleResponseMapper extends DtoMapperFacade<Role, RoleResponseDto> {
    public RoleResponseMapper() {
        super(Role.class, RoleResponseDto.class);
    }

}
