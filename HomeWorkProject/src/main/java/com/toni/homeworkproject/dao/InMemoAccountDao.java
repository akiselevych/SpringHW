package com.toni.homeworkproject.dao;

import com.toni.homeworkproject.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoAccountDao implements DefaultDao<Account> {
    private final static AtomicLong id = new AtomicLong(1);
    private final List<Account> accounts = new ArrayList<>();

    @Override
    public List<Account> findAll() {
        return this.accounts;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accounts.stream().filter(acc -> acc.getId().equals(id)).findAny();
    }

    @Override
    public Account create(Account obj) {
        obj.setId(id.getAndIncrement());
        accounts.add(obj);
        return obj;
    }

    @Override
    public void createAll(List<Account> entities) {
        entities.forEach(acc -> {
            acc.setId(id.getAndIncrement());
            accounts.add(acc);
        });
    }

    @Override
    public Account update(Account obj) {
        accounts.stream()
                .filter(acc -> acc.getId().equals(obj.getId()))
                .forEach(acc -> acc = obj);

        return obj;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Account> account = findById(id);
        if (account.isPresent()){
            accounts.remove(account.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Account obj) {
        return accounts.remove(obj);
    }

    @Override
    public void deleteAll(List<Account> entities) {
        entities.forEach(accounts::remove);
    }
}
