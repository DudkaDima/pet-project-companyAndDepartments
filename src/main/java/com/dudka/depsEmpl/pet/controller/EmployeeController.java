package com.dudka.depsEmpl.pet.controller;

import com.dudka.depsEmpl.pet.dto.EmployeeDto;
import com.dudka.depsEmpl.pet.dto.mapper.MapEmployeeToDto;
import com.dudka.depsEmpl.pet.model.Employee;
import com.dudka.depsEmpl.pet.repository.DepRepository;
import com.dudka.depsEmpl.pet.repository.EmpRoleDepRepository;
import com.dudka.depsEmpl.pet.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/employee", method = RequestMethod.GET)
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final DepRepository depRepository;
    private final EmpRoleDepRepository empRoleDepRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, DepRepository depRepository, EmpRoleDepRepository empRoleDepRepository) {
        this.employeeRepository = employeeRepository;
        this.depRepository = depRepository;
        this.empRoleDepRepository = empRoleDepRepository;
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
        return ResponseEntity.ok(MapEmployeeToDto.map(employee));
    }

    @GetMapping(value = "/getByDep/{department_id}")
    public ResponseEntity getByDepId(@PathVariable(value = "department_id") Long id) {
        List<Optional<Employee>> employees = this.employeeRepository.findByEmpRoleDepDepartmentId_Id(id);
        List<EmployeeDto> employeeDtos =  employees.stream()
                .map(MapEmployeeToDto::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDtos);

    }

    @GetMapping(value = "/getByCompany/{company_id}")
    public ResponseEntity getByCompanyId(@PathVariable(value = "company_id") Long id) {
        List<Optional<Employee>> employees = this.employeeRepository.findByEmpRoleDepCompanyId_Id(id);
        List<EmployeeDto> employeeDtos =  employees.stream()
                .map(MapEmployeeToDto::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDtos);

    }


    @GetMapping(value = "/findByFirstName")
    public ResponseEntity getEmpByFirstName(@RequestParam(value = "firstName") String firstName) {
        Optional<Employee> employee = this.employeeRepository.findByFirstName(firstName);
        return ResponseEntity.ok(MapEmployeeToDto.map(employee));

    }

    @RequestMapping (value = "/fireDep", method = RequestMethod.DELETE)
    public RedirectView fireDep(@RequestParam(value = "id")  String id) {
        String pathParams = id;
        List<String> idList = List.of(id.split(","));
        this.empRoleDepRepository.deleteByDepartment_id(Long.valueOf(idList.get(0)), Long.valueOf(idList.get(1)));
        if(!this.empRoleDepRepository.existsByEmployee_Id(Long.valueOf(idList.get(1)))) {
            employeeRepository.deleteById(Long.valueOf(idList.get(1)));
        }
        return new RedirectView("/employee");
    }

    @RequestMapping (value = "/fireCompany", method = RequestMethod.DELETE)
    public RedirectView fireCompany(@RequestParam(value = "id")  String id) {
        String pathParams = id;
        List<String> idList = List.of(id.split(","));
        this.empRoleDepRepository.deleteByCompany_id(Long.valueOf(idList.get(0)), Long.valueOf(idList.get(1)));
        if(!this.empRoleDepRepository.existsByEmployee_Id(Long.valueOf(idList.get(1)))) {
            employeeRepository.deleteById(Long.valueOf(idList.get(1)));
        }
        return new RedirectView("/employee");
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try{
            Employee employeeForAdd  = this.employeeRepository.save(new Employee(employee.getFirstName(),
                    employee.getLastName(), employee.getEmail(), employee.getPassword(), employee.getPhone()));
            return new ResponseEntity<>(employeeForAdd, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
