package com.testspring.employeemanagerapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testspring.employeemanagerapp.model.Employee;




public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    void deleteEmployeeById(Long id);

    Optional <Employee> findEmployeeById(Long id);
    Optional <Employee> findEmployeeByEmail(String email);
    
}
