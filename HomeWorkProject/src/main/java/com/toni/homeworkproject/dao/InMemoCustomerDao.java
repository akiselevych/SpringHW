package com.toni.homeworkproject.dao;


import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.Customer;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class InMemoCustomerDao implements DefaultDao<Customer> {
    private final EntityManagerFactory emf;

    @Override
    public List<Customer> findAll() {
        try(EntityManager entityManager = emf.createEntityManager()){
            EntityGraph<?> withAccounts = entityManager.getEntityGraph("customerWithAccountsAndEmployers");
            return entityManager.createQuery("select a from Customer a", Customer.class)
                    .setHint("jakarta.persistence.fetchgraph",withAccounts).getResultList();
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try(EntityManager entityManager = emf.createEntityManager()){
            EntityGraph<?> graph = entityManager.getEntityGraph("customerWithAccountsAndEmployers");
            Map<String, Object> props = new HashMap<>();
            props.put("jakarta.persistence.fetchgraph", graph);
            return Optional.ofNullable(entityManager.find(Customer.class,id,props));
        }
    }

    @Override
    public Customer create(Customer obj) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = emf.createEntityManager()){
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(obj);
            transaction.commit();
            entityManager.refresh(obj);
            return findById(obj.getId()).get();
        } catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            return null;
        }
    }

    @Override
    public void createAll(List<Customer> entities) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = emf.createEntityManager()){
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entities);
            transaction.commit();
            entityManager.refresh(entities);
        } catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public Customer update(Customer obj) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = emf.createEntityManager()){
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(obj);
            transaction.commit();
            return findById(obj.getId()).get();
        } catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = emf.createEntityManager()){
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.find(Customer.class,id));
            transaction.commit();
            return true;
        } catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean delete(Customer obj) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = emf.createEntityManager()){
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(obj);
            transaction.commit();
            return true;
        } catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public void deleteAll(List<Customer> entities) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = emf.createEntityManager()){
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entities);
            transaction.commit();
        } catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }
}
