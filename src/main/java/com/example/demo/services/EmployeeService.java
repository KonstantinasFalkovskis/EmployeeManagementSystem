package com.example.demo.services;

import com.example.demo.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    //find exist emloyees by name with pagination
    Page<Employee> findByName(String name, Pageable pageable);

    //find exist employees list with pagination
    Page<Employee> findAll(Pageable pageable);

    
}
