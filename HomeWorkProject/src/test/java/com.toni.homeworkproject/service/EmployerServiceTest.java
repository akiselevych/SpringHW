package com.toni.homeworkproject.service;

import com.toni.homeworkproject.dao.EmployerJpaRepository;
import com.toni.homeworkproject.domain.Employer;
import com.toni.homeworkproject.domain.Employer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployerServiceTest {

    @Mock
    private EmployerJpaRepository employerJpaRepository;

    @Captor
    private ArgumentCaptor<Employer> argumentCaptor;
    @InjectMocks
    private EmployerService employerService;

    @Test
    public void paginationAndSortFindAllTest(){
        Employer Employer = new Employer();
        Employer.setId(1L);
        Employer Employer2 = new Employer();
        Employer2.setId(3L);
        Employer Employer3 = new Employer();
        Employer3.setId(5L);
        Employer Employer4 = new Employer();
        Employer4.setId(2L);
        when(employerJpaRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(Employer,Employer4,Employer2)));
        List<Employer> Employers = employerService.findAll(0,3);
        assertEquals(List.of(Employer,Employer4,Employer2),Employers);
    }

    @Test
    public void findByExistingIdTest(){
        Employer Employer = new Employer();
        Employer.setId(4L);

        when(employerJpaRepository.findById(4L)).thenReturn(Optional.of(Employer));

        Optional<Employer> EmployerOptional = employerService.findById(4L);

        assertEquals(Employer, EmployerOptional.get());
    }

    @Test
    public void findByNotExistingIdTest(){

        when(employerJpaRepository.findById(4L)).thenReturn(Optional.empty());

        Optional<Employer> EmployerOptional = employerService.findById(4L);

        assertThrows(NoSuchElementException.class, EmployerOptional::get);
    }

    @Test
    public void findByNullIdTest(){

        when(employerJpaRepository.findById(null)).thenReturn(Optional.empty());

        Optional<Employer> EmployerOptional = employerService.findById(null);

        assertThrows(NoSuchElementException.class, EmployerOptional::get);
    }

    @Test
    public void createNewEmployerTest(){
        Employer Employer = new Employer();
        when(employerJpaRepository.save(Employer)).thenReturn(Employer);
        Employer createdEmployer = employerService.create(Employer);

        assertEquals(createdEmployer,Employer);
    }

    @Test
    public void createNewEmployerCaptorTest(){
        Employer Employer = new Employer();
        employerService.create(Employer);

        verify(employerJpaRepository).save(argumentCaptor.capture());

        Employer captedEmployer = argumentCaptor.getValue();
        assertEquals(Employer,captedEmployer);
    }

    @Test
    public void createNullEmployerTest(){
        when(employerJpaRepository.save(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,() -> employerService.create(null));
    }

    @Test
    public void updateEmployerTest(){
        Employer Employer = new Employer();

        when(employerJpaRepository.save(Employer)).thenReturn(Employer);

        assertEquals(Employer, employerService.update(Employer));
    }
}
