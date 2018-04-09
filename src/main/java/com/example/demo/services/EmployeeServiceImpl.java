/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.services;

import com.example.demo.entities.Departament;
import com.example.demo.entities.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Methods for Employee entity from EmployeeService implementation
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    //Employee repo calling. There are default save, delete, find etc. methods
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Employee finding from data base by name value, also with pagination possibility;
     * @param name
     * @param pageable
     * @return
     */
    @Override
    public Page<Employee> findByName(String name, Pageable pageable) {
        return employeeRepository.findByNameLike("%"+name+"%", pageable);
    }

    /**
     * list of employee calling + pagination;
     * @param pageable
     * @return
     */
    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    /**
     * calling employee by id value
     * @param id
     * @return
     */
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    /**
     * employee removing from data base by id value
     * @param id
     */
    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    /**
     * employee adding into data base
     * @param employee
     * @return
     */
    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> employeeList() {
        return employeeRepository.findAll();
    }

    public boolean isEmployeePresent(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null)
            return true;
        return false;
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeRepository.findByNameLike("%"+name+"%");
    }
}
