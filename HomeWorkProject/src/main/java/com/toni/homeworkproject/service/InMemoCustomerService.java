package com.toni.homeworkproject.service;

import com.toni.homeworkproject.dao.DefaultDao;
import com.toni.homeworkproject.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InMemoCustomerService implements DefaultService<Customer> {
    private final DefaultDao<Customer> customerDao;

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public Customer create(Customer obj) {
        return customerDao.create(obj);
    }

    @Override
    public void createAll(List<Customer> entities) {
        customerDao.createAll(entities);
    }

    @Override
    public Customer update(Customer obj) {
        return customerDao.update(obj);
    }

    @Override
    public boolean delete(Long id) {
        return customerDao.delete(id);
    }

    @Override
    public boolean delete(Customer obj) {
        return customerDao.delete(obj);
    }

    @Override
    public void deleteAll(List<Customer> entities) {
        customerDao.deleteAll(entities);
    }
}
