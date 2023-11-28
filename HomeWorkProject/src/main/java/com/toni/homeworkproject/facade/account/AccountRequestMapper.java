package com.toni.homeworkproject.facade.account;

import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.dtos.request.AccountRequestDto;
import com.toni.homeworkproject.facade.DtoMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class AccountRequestMapper extends DtoMapperFacade<Account, AccountRequestDto> {
    public AccountRequestMapper() {
        super(Account.class, AccountRequestDto.class);
    }
}
