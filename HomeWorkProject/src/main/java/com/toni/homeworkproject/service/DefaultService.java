package com.toni.homeworkproject.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface DefaultService<T> {
    List<T> findAll(Sort sort);
    List<T> findAll();
    List<T> findAll(int page,int quantity);

    Optional<T> findById(Long id);
    T create(T obj);
    void createAll(List<T> entities);
    T update(T obj);
    void delete(Long id);
    void delete(T obj);
    void deleteAll(List<T> entities);

    default T findByNum() {
        return null;
    }
}
