package com.toni.homeworkproject.facade.account;

import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.dtos.request.AccountRequestDto;
import com.toni.homeworkproject.domain.dtos.response.AccountResponseDto;
import com.toni.homeworkproject.facade.DtoMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class AccountResponseMapper extends DtoMapperFacade<Account, AccountResponseDto> {
    public AccountResponseMapper() {
        super(Account.class, AccountResponseDto.class);
    }
}
