package com.dudka.depsEmpl.pet.repository;

import com.dudka.depsEmpl.pet.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepRepository extends JpaRepository<Department, Long> {

}
