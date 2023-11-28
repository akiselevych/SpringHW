package com.toni.homeworkproject.service;

import com.toni.homeworkproject.dao.CustomerJpaRepository;
import com.toni.homeworkproject.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InMemoCustomerService implements DefaultService<Customer> {
    private final CustomerJpaRepository customerRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
    @Transactional
    @Override
    public Customer create(Customer obj) {
        return customerRepository.save(obj);
    }
    @Transactional
    @Override
    public void createAll(List<Customer> entities) {
        customerRepository.saveAll(entities);
    }

    @Transactional
    @Override
    public Customer update(Customer obj) {
        return customerRepository.save(obj);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void delete(Customer obj) {
        customerRepository.delete(obj);
    }

    @Transactional
    @Override
    public void deleteAll(List<Customer> entities) {
        customerRepository.deleteAll(entities);
    }
}
