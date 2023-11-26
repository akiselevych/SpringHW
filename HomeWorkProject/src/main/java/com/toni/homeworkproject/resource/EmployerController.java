package com.toni.homeworkproject.resource;

import com.toni.homeworkproject.domain.Employer;
import com.toni.homeworkproject.service.DefaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employers")
@RequiredArgsConstructor
public class EmployerController {
    private final DefaultService<Employer> empService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(empService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.of(empService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employer employer){
        return ResponseEntity.ok(empService.create(employer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(empService.delete(id));
    }
}
