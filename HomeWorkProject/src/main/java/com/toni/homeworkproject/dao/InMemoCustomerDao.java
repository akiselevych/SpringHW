package com.toni.homeworkproject.dao;


import com.toni.homeworkproject.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoCustomerDao implements DefaultDao<Customer> {
    private final static AtomicLong id = new AtomicLong(1);
    private final List<Customer> customers = new ArrayList<>();
    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findAny();
    }

    @Override
    public Customer create(Customer obj) {
        obj.setId(id.getAndIncrement());
        customers.add(obj);
        return obj;
    }

    @Override
    public void createAll(List<Customer> entities) {
        entities.forEach(acc -> {
            acc.setId(id.getAndIncrement());
            customers.add(acc);
        });
    }

    @Override
    public Customer update(Customer obj) {
        customers.replaceAll(customer -> {
            if (customer.getId().equals(obj.getId())){
                return obj;
            }
            return customer;
        });

        return obj;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Customer> customer = findById(id);
        if (customer.isPresent()){
            customers.remove(customer.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Customer obj) {
        return customers.remove(obj);
    }

    @Override
    public void deleteAll(List<Customer> entities) {
        entities.forEach(customers::remove);
    }
}
