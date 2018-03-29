/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.services;

import com.example.demo.entities.Departament;
import com.example.demo.repository.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Methods implementations for Department entity
 */
@Service
public class DepartamentServiceImpl implements DepartamentService {

    //Department repository reaching
    @Autowired
    private DepartamentRepository departamentRepository;

    //Department service calling
    @Autowired
    private DepartamentService departamentService;

    //department from data base by depo name. Pagination included also;
    public Page<Departament> findDepoByName(String departament, Pageable pageable) {
        return departamentService.findDepoByName("%" + departament + "%", pageable);
        }

    //departments list from data base + pagination;
    @Override
    public Page<Departament> findAll(Pageable pageable) {
        return departamentRepository.findAll(pageable);
    }

    //department calling from list by id
    @Override
    public Departament findOne(Long id) {
        return departamentRepository.findById(id).orElse(null);
    }

    //department adding to data base
    @Override
    public Departament saveDepo(Departament departament) {
        return departamentRepository.save(departament);
    }

    //department removing from data base by id value
    @Override
    public void deleteDepo(Long id) {
        departamentRepository.deleteById(id);
    }
    //get list of departments
    @Override
    public Iterable<Departament> findListDepartment() {
        return departamentRepository.findAll();
    }
}
