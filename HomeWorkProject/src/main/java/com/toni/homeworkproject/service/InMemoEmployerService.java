package com.toni.homeworkproject.service;

import com.toni.homeworkproject.dao.DefaultDao;
import com.toni.homeworkproject.domain.Employer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InMemoEmployerService implements DefaultService<Employer> {
    private final DefaultDao<Employer> employerDao;


    @Override
    public List<Employer> findAll() {
        return employerDao.findAll();
    }

    @Override
    public Optional<Employer> findById(Long id) {
        return employerDao.findById(id);
    }


    @Override
    public Employer create(Employer obj) {
        return employerDao.create(obj);
    }

    @Override
    public void createAll(List<Employer> entities) {
        employerDao.createAll(entities);
    }

    @Override
    public Employer update(Employer obj) {
        return employerDao.update(obj);
    }

    @Override
    public boolean delete(Long id) {
        return employerDao.delete(id);
    }

    @Override
    public boolean delete(Employer obj) {
        return employerDao.delete(obj);
    }

    @Override
    public void deleteAll(List<Employer> entities) {
        employerDao.deleteAll(entities);
    }
}
