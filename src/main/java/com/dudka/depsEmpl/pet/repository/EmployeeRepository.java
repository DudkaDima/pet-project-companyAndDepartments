package com.dudka.depsEmpl.pet.repository;

import com.dudka.depsEmpl.pet.dto.EmployeeDto;
import com.dudka.depsEmpl.pet.dto.mapper.MapEmployeeToDto;
import com.dudka.depsEmpl.pet.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.firstName = :#{#firstName}")
    public Optional<Employee> findByFirstName(@Param("firstName") String firstName);

    @Query("SELECT e FROM Employee e WHERE e.email = :#{#email}")
    public Optional<Employee> findByEmail(@Param("email") String email);

//    @Query("select e from emp_role_dep join employee on emp_role_dep.emp_id=employee.id join department on emp_role_dep.department_id=department.id join company on company.id=department.company_id where emp_role_dep.department_id = :#{#department_id}")
    public List<Optional<Employee>> findByEmpRoleDepDepartmentId_Id(@Param("department_id") Long id);

}
