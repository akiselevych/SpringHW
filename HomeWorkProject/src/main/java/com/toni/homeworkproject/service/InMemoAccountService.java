package com.toni.homeworkproject.service;
import org.springframework.stereotype.Service;
import com.toni.homeworkproject.dao.DefaultDao;
import com.toni.homeworkproject.domain.Account;

import java.util.List;
import java.util.Optional;

@Service
public class InMemoAccountService implements DefaultService<Account> {
    private final DefaultDao<Account> accountDao;

    public InMemoAccountService(DefaultDao<Account> accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountDao.findById(id);
    }


    @Override
    public Account create(Account obj) {
        return accountDao.create(obj);
    }

    @Override
    public void createAll(List<Account> entities) {
        accountDao.createAll(entities);
    }

    @Override
    public Account update(Account obj) {
        return accountDao.update(obj);
    }

    @Override
    public boolean delete(Long id) {
        return accountDao.delete(id);
    }

    @Override
    public boolean delete(Account obj) {
        return accountDao.delete(obj);
    }

    @Override
    public void deleteAll(List<Account> entities) {
        accountDao.deleteAll(entities);
    }
}
