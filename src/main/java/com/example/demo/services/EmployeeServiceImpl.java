/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.services;

import com.example.demo.entities.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Methods for Employee entity from EmployeeService implementation
 */
public class EmployeeServiceImpl implements EmployeeService {

    //Employee repo calling. There are default save, delete, find etc. methods
    @Autowired
    private EmployeeRepository employeeRepository;

    //Employee finding from data base by name value, also with pagination possibility;
    @Override
    public Page<Employee> findByName(String name, Pageable pageable) {
        return employeeRepository.findByName(name, pageable);
    }

    //list of employee calling + pagination;
    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    //calling employee by id value
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    //employee removing from data base by id value
    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    //employee adding into data base
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
