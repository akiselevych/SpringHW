package com.toni.homeworkproject.service;

import com.toni.homeworkproject.dao.EmployerJpaRepository;
import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.Employer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InMemoEmployerService implements DefaultService<Employer> {
    private final EmployerJpaRepository employerRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Employer> findAll(Sort sort) {
        return employerRepository.findAll(sort);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employer> findAll() {
         return employerRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employer> findAll(int page,int quantity) {
        Page<Employer> pageList = employerRepository.findAll(PageRequest.of(page, quantity));
        return pageList.toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Employer> findById(Long id) {
        return employerRepository.findById(id);
    }
    @Transactional
    @Override
    public Employer create(Employer obj) {
        return employerRepository.save(obj);
    }
    @Transactional
    @Override
    public void createAll(List<Employer> entities) {
        employerRepository.saveAll(entities);
    }

    @Transactional
    @Override
    public Employer update(Employer obj) {
        return employerRepository.save(obj);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        employerRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void delete(Employer obj) {
        employerRepository.delete(obj);
    }

    @Transactional
    @Override
    public void deleteAll(List<Employer> entities) {
        employerRepository.deleteAll(entities);
    }
}
