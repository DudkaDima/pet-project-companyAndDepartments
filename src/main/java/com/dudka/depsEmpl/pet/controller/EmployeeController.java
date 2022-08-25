package com.dudka.depsEmpl.pet.controller;

import com.dudka.depsEmpl.pet.dto.EmployeeDto;
import com.dudka.depsEmpl.pet.dto.mapper.MapEmployeeToDto;
import com.dudka.depsEmpl.pet.model.Employee;
import com.dudka.depsEmpl.pet.repository.DepRepository;
import com.dudka.depsEmpl.pet.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
        List<Employee> employees = this.employeeRepository.findAll();
        List<EmployeeDto> employeeDtos =  employees.stream()
                .map(p -> MapEmployeeToDto.map(Optional.ofNullable(p)))
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping(value = "/deps")
    public ResponseEntity getDep() {
        return ResponseEntity.ok(this.depRepository.findAll());
    }

    @GetMapping(value = "/findByEmail")
    public ResponseEntity getEmpByEmail(@RequestParam(value = "email") String email) {
        Optional<Employee> employee = this.employeeRepository.findByEmail(email);
        EmployeeDto employeeDto = MapEmployeeToDto.map(employee);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping(value = "/getByDep/{department_id}")
    public ResponseEntity getByDepId(@PathVariable(value = "department_id") Long id) {
        List<Optional<Employee>> employees = this.employeeRepository.findByEmpRoleDepDepartmentId_Id(id);
        List<EmployeeDto> employeeDtos =  employees.stream()
                .map(MapEmployeeToDto::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDtos);

    }

    @GetMapping(value = "/findByFirstName")
    public ResponseEntity getEmpByFirstName(@RequestParam(value = "firstName") String firstName) {
        Optional<Employee> employee = this.employeeRepository.findByFirstName(firstName);
        EmployeeDto employeeDto = MapEmployeeToDto.map(employee);
        return ResponseEntity.ok(employeeDto);

    }
}
