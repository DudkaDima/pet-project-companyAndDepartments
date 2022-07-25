package com.dudka.depsEmpl.pet.controller;

import com.dudka.depsEmpl.pet.repository.DepRepository;
import com.dudka.depsEmpl.pet.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final DepRepository depRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, DepRepository depRepository) {
        this.employeeRepository = employeeRepository;
        this.depRepository = depRepository;
    }

    @GetMapping
    public ResponseEntity getEmployee() {
        return ResponseEntity.ok(this.employeeRepository.findAll());
    }

    @GetMapping(value = "/deps")
    public ResponseEntity getDep() {
        return ResponseEntity.ok(this.depRepository.findAll());
    }
}
