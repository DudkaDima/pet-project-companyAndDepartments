package com.dudka.depsEmpl.pet.dto;

import com.dudka.depsEmpl.pet.model.EmpRoleDep;
import com.dudka.depsEmpl.pet.model.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class EmployeeDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private List<EmpRoleDep> empRoleDep;

    public EmployeeDto(Optional<Employee> employee) {
        this.id = employee.get().getId();
        this.firstName = employee.get().getFirstName();
        this.lastName = employee.get().getLastName();
        this.email = employee.get().getEmail();
        this.phone = employee.get().getPhone();
        this.empRoleDep = employee.get().getEmpRoleDep();
    }
}
