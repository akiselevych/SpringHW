package com.toni.homeworkproject.resource;

import com.toni.homeworkproject.domain.Employer;
import com.toni.homeworkproject.domain.dtos.request.EmployerRequestDto;
import com.toni.homeworkproject.domain.dtos.response.EmployerResponseDto;
import com.toni.homeworkproject.facade.employer.EmployerRequestMapper;
import com.toni.homeworkproject.facade.employer.EmployerResponseMapper;
import com.toni.homeworkproject.service.DefaultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employers")
@RequiredArgsConstructor
@Slf4j
public class EmployerController {
    private final DefaultService<Employer> empService;
    private final EmployerResponseMapper employerResponseMapper;
    private final EmployerRequestMapper employerRequestMapper;

    @GetMapping
    public ResponseEntity<?> findAll(){
        log.info("Find all method");
        List<Employer> employers = empService.findAll(Sort.by(Sort.Direction.ASC,"id"));
        List<EmployerResponseDto> dtos = employers.stream().map(employerResponseMapper::convertToDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id){
        log.info("Find by id method");
        Optional<Employer> employerOptional = empService.findById(id);
        if (employerOptional.isPresent()){
            return ResponseEntity.ok(employerResponseMapper.convertToDto(employerOptional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employer not found");
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployerRequestDto employer){
        log.info("Employer created");
        return ResponseEntity.ok(employerResponseMapper.convertToDto(empService.create(employerRequestMapper.convertToEntity(employer))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        log.info("Employer deleted");
        return ResponseEntity.status(204).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptions(Exception e){
        log.warn(e.getMessage());
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
