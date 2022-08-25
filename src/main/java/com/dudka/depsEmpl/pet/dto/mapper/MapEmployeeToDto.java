package com.dudka.depsEmpl.pet.dto.mapper;

import com.dudka.depsEmpl.pet.dto.EmployeeDto;
import com.dudka.depsEmpl.pet.model.Employee;

import java.util.Optional;

public class MapEmployeeToDto {

    public static EmployeeDto map(Optional<Employee> employee) {
        return new EmployeeDto(employee);
    }
}
