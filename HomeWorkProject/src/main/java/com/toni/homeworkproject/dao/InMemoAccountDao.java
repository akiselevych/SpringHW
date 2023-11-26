package com.toni.homeworkproject.dao;

import com.toni.homeworkproject.domain.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class InMemoAccountDao implements DefaultDao<Account> {
    private final EntityManagerFactory emf;

    @Override
    public List<Account> findAll() {
        try(EntityManager entityManager = emf.createEntityManager()){
            return entityManager.createQuery("select a from Account a", Account.class).getResultList();
        }
    }

    @Override
    public Optional<Account> findById(Long id) {
        try(EntityManager entityManager = emf.createEntityManager()){
            return Optional.ofNullable(entityManager.find(Account.class,id));
        }
    }

    @Override
    public Account create(Account obj) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(obj);
            transaction.commit();
            entityManager.refresh(obj);
            return obj;
        } catch (HibernateException e){
            if (transaction != null){
                transaction.rollback();
            }
            return null;
        }
    }

    @Override
    public void createAll(List<Account> entities) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = emf.createEntityManager()) {
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
    public Account update(Account obj) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(obj);
            transaction.commit();
            return obj;
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
        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.find(Account.class, id));
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
    public boolean delete(Account obj) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = emf.createEntityManager()) {
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
    public void deleteAll(List<Account> entities) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = emf.createEntityManager()) {
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
