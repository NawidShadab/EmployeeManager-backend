package com.testspring.employeemanagerapp.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.print.attribute.standard.PrinterInfo;
import javax.xml.transform.sax.SAXSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testspring.employeemanagerapp.exception.UserAlreadyExistException;
import com.testspring.employeemanagerapp.exception.UserNotFoundException;
import com.testspring.employeemanagerapp.model.Employee;
import com.testspring.employeemanagerapp.repo.EmployeeRepo;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;


    //@Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }



    public Employee addEmployee(Employee employee) {
        Optional<Employee> employeeEmail = employeeRepo.findEmployeeByEmail(employee.getEmail());
        
        //System.out.println("hereeeee" + employeeEmail);
        if (employeeEmail.isPresent()) {
            throw new UserAlreadyExistException("user with this email already exists!");
        }

        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee); 
    }




    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }



    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    // public Employee updateEmployee(Long employeeId, String name, String email, String phone, String imageUrl){
    //     Employee employee = employeeRepo.findById(employeeId)
    //     .orElseThrow(() -> new UserNotFoundException("Employee with id= " + employeeId + " does not exist"));

    //     // check and save name
    //     if (name != null && name.length() > 0 && !Objects.equals(employee.getName(), name)) {
    //         employee.setName(name);
    //     }

    //     // check and save email
    //     if (email != null && email.length() > 0 && !Objects.equals(employee.getEmail(), email)) {

    //         Optional<Employee> employeeOptional = employeeRepo.findEmployeeByEmail(email);
    //         if (employeeOptional.isPresent()){
    //             throw new IllegalStateException("user with this email already exists!");
    //         }

    //         employee.setEmail(email);
    //     }

    //     // check and save phone
    //     if (phone != null && phone.length() > 0 && !Objects.equals(employee.getPhone(), phone)) {
    //         employee.setPhone(phone);
    //     }


    //     // check and save imageUrl
    //     if (imageUrl != null && imageUrl.length() > 0 && !Objects.equals(employee.getImageUrl(), name)) {
    //         employee.setImageUrl(name);
    //     }

    //     return employeeRepo.save(employee);
    // }



    public Employee findEmployeeById(Long id){
        return employeeRepo.findEmployeeById(id)
            .orElseThrow(() -> new UserNotFoundException("User by id " + id + "not found"));       
    }



    @Transactional
    public void deleteEmployee(Long employeeId){
        boolean exist = employeeRepo.existsById(employeeId);
        if (!exist){
            throw new UserNotFoundException("Employee by id " + employeeId + " not found");
        }
        employeeRepo.deleteEmployeeById(employeeId);
    }


}
