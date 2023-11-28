package com.toni.homeworkproject.service;
import com.toni.homeworkproject.dao.AccountJpaRepository;
import com.toni.homeworkproject.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.toni.homeworkproject.domain.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InMemoAccountService implements DefaultService<Account> {
    private final AccountJpaRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }
    @Transactional
    @Override
    public Account create(Account obj) {
        return accountRepository.save(obj);
    }
    @Transactional
    @Override
    public void createAll(List<Account> entities) {
        accountRepository.saveAll(entities);
    }

    @Transactional
    @Override
    public Account update(Account obj) {
        return accountRepository.save(obj);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void delete(Account obj) {
        accountRepository.delete(obj);
    }

    @Transactional
    @Override
    public void deleteAll(List<Account> entities) {
        accountRepository.deleteAll(entities);
    }
}
