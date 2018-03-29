/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.services;

import com.example.demo.entities.Departament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Department services defining into abstract class
 */
public interface DepartamentService {

    //get department by name
    Page<Departament> findDepoByName(String departament, Pageable pageable);

    //get exist deprtaments list
    Page<Departament> findAll(Pageable pageable);

    Iterable<Departament> findListDepartment();

    //get department info by id
    Departament findOne(Long id);

    //save department into DB
    Departament saveDepo(Departament departament);

    //remove department from data base
    void deleteDepo(Long id);


}
