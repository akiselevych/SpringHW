package com.toni.homeworkproject.facade.account;

import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.dtos.response.MessageAccountResponseDto;
import com.toni.homeworkproject.facade.DtoMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class MessageAccountResponseMapper extends DtoMapperFacade<Account, MessageAccountResponseDto> {

    public MessageAccountResponseMapper() {
        super(Account.class, MessageAccountResponseDto.class);
    }
}
