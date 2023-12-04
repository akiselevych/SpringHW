package com.toni.homeworkproject.service;

import com.toni.homeworkproject.dao.AccountJpaRepository;
import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountJpaRepository accountJpaRepository;

    @Captor
    private ArgumentCaptor<Account> argumentCaptor;
    @InjectMocks
    private AccountService accountService;

    @Test
    public void paginationAndSortFindAllTest(){
        Account Account = new Account();
        Account.setId(1L);
        Account Account2 = new Account();
        Account2.setId(3L);
        Account Account3 = new Account();
        Account3.setId(5L);
        Account Account4 = new Account();
        Account4.setId(2L);
        when(accountJpaRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(Account,Account4,Account2)));
        List<Account> Accounts = accountService.findAll(0,3);
        assertEquals(List.of(Account,Account4,Account2),Accounts);
    }

    @Test
    public void findByExistingIdTest(){
        Account Account = new Account();
        Account.setId(4L);

        when(accountJpaRepository.findById(4L)).thenReturn(Optional.of(Account));

        Optional<Account> AccountOptional = accountService.findById(4L);

        assertEquals(Account, AccountOptional.get());
    }

    @Test
    public void findByNotExistingIdTest(){

        when(accountJpaRepository.findById(4L)).thenReturn(Optional.empty());

        Optional<Account> AccountOptional = accountService.findById(4L);

        assertThrows(NoSuchElementException.class, AccountOptional::get);
    }

    @Test
    public void findByNullIdTest(){

        when(accountJpaRepository.findById(null)).thenReturn(Optional.empty());

        Optional<Account> AccountOptional = accountService.findById(null);

        assertThrows(NoSuchElementException.class, AccountOptional::get);
    }

    @Test
    public void createNewAccountTest(){
        Account Account = new Account();
        when(accountJpaRepository.save(Account)).thenReturn(Account);
        Account createdAccount = accountService.create(Account);

        assertEquals(createdAccount,Account);
    }

    @Test
    public void createNewAccountCaptorTest(){
        Account Account = new Account();
        accountService.create(Account);

        verify(accountJpaRepository).save(argumentCaptor.capture());

        Account captedAccount = argumentCaptor.getValue();
        assertEquals(Account,captedAccount);
    }

    @Test
    public void createNullAccountTest(){
        when(accountJpaRepository.save(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,() -> accountService.create(null));
    }

    @Test
    public void updateAccountTest(){
        Account Account = new Account();

        when(accountJpaRepository.save(Account)).thenReturn(Account);

        assertEquals(Account, accountService.update(Account));
    }
}
