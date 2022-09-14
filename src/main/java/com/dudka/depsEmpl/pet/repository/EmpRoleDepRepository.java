package com.dudka.depsEmpl.pet.repository;

import com.dudka.depsEmpl.pet.model.EmpRoleDep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EmpRoleDepRepository extends JpaRepository<EmpRoleDep, Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE From emp_role_dep erd WHERE  erd.department_id = :#{#department_id} AND erd.emp_id = :#{#emp_id}")
    public void deleteByDepartment_id(@Param("department_id") Long depId, @Param("emp_id") Long empId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE From emp_role_dep erd WHERE  erd.company_id = :#{#company_id} AND erd.emp_id = :#{#emp_id}")
    public void deleteByCompany_id(@Param("company_id") Long depId, @Param("emp_id") Long empId);

    public boolean existsByEmployee_Id(@Param("emp_id") Long empId);
}
