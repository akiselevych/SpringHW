package com.toni.homeworkproject.dao;

import java.util.List;
import java.util.Optional;

public interface DefaultDao<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    T create(T obj);
    void createAll(List<T> entities);
    T update(T obj);
    boolean delete(Long id);
    boolean delete(T obj);
    void deleteAll(List<T> entities);
}
