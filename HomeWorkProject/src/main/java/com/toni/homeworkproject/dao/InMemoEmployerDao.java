package com.toni.homeworkproject.dao;

import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.Employer;
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
public class InMemoEmployerDao implements DefaultDao<Employer> {

    private final EntityManagerFactory emf;

    @Override
    public List<Employer> findAll() {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return entityManager.createQuery("select a from Employer a", Employer.class).getResultList();
        }
    }

    @Override
    public Optional<Employer> findById(Long id) {
        try (EntityManager entityManager = emf.createEntityManager()) {
            return Optional.ofNullable(entityManager.find(Employer.class, id));
        }
    }

    @Override
    public Employer create(Employer obj) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = emf.createEntityManager()){
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
    public void createAll(List<Employer> entities) {
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
    public Employer update(Employer obj) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = emf.createEntityManager()){
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(obj);
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
    public boolean delete(Employer obj) {
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
    public void deleteAll(List<Employer> entities) {
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
