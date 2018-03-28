/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.services;

import com.example.demo.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Employees services
 */
@Service
public interface EmployeeService {

    //find exist emloyees by name with pagination
    public Page<Employee> findByName(String name, Pageable pageable);

    //get exist employees list with pagination
    public Page<Employee> findAll(Pageable pageable);

    //get employee by id
    public Employee getEmployeeById(Long id);

    //delete employee by id
    public void deleteEmployee(Long id);

    //save employee
    public Employee saveEmployee(Employee employee);

}
