package com.dudka.depsEmpl.pet.repository;

import com.dudka.depsEmpl.pet.model.Employee;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    public List<Employee> findByFirstNameAndEmpRoleDeps(String name, Long Id);

}
