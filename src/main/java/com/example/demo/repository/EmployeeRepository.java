/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.repository;

import com.example.demo.entities.Employee;
import com.example.demo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //employee searching by name value
//    Page<Employee> findByName(String name, Pageable pageable);
    Page<Employee> findByNameLike(String name, Pageable pageable);
    List<Employee> findByNameLike(String name);

}
