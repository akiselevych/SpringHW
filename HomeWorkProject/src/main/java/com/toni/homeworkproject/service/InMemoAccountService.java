package com.toni.homeworkproject.service;
import com.toni.homeworkproject.dao.AccountJpaRepository;
import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.Employer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAll(int page,int quantity) {
        Page<Account> pageList = accountRepository.findAll(PageRequest.of(page, quantity));
        return pageList.toList();
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
