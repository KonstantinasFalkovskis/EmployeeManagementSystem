/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.services;

import com.example.demo.entities.Departament;
import com.example.demo.entities.Employee;
import com.example.demo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Employees services
 */
public interface EmployeeService {

    //find exist emloyees by name with pagination
    Page<Employee> findByName(String name, Pageable pageable);

    //get exist employees list with pagination
    Page<Employee> findAll(Pageable pageable);

    List<Employee> findByName(String name);

    //get employee by id
    Employee getEmployeeById(Long id);

    //delete employee by id
    void deleteEmployee(Long id);

    //save employee
    Employee saveEmployee(Employee employee);

    //list of employee
    List<Employee> employeeList();

}
