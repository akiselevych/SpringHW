package com.toni.homeworkproject.service;

import java.util.List;
import java.util.Optional;

public interface DefaultService<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    T create(T obj);
    void createAll(List<T> entities);
    T update(T obj);
    boolean delete(Long id);
    boolean delete(T obj);
    void deleteAll(List<T> entities);

    default T findByNum() {
        return null;
    }
}
