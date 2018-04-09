/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.services;

import com.example.demo.entities.Departament;
import com.example.demo.entities.User;
import com.example.demo.repository.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Methods implementations for Department entity
 */
@Service
public class DepartamentServiceImpl implements DepartamentService {

    //Department repository reaching
    @Autowired
    private DepartamentRepository departamentRepository;

    //Department services calling
    @Autowired
    private DepartamentService departamentService;

    /**
     * department from data base by depo name. Pagination included also;
     *
     * @param departament
     * @param pageable
     * @return
     */
    public Page<Departament> findDepoByName(String departament, Pageable pageable) {
        return departamentService.findDepoByName("%" + departament + "%", pageable);
    }

    /**
     * departments list from data base + pagination;
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Departament> findAll(Pageable pageable) {
        return departamentRepository.findAll(pageable);
    }

    /**
     * department calling from list by id
     *
     * @param id
     * @return
     */
    @Override
    public Departament findOne(Long id) {
        return departamentRepository.findById(id).orElse(null);
    }

    /**
     * department saving method which saves depo into the data base
     *
     * @param departament
     * @return
     */
    @Override
    @Transactional
    public Departament saveDepo(Departament departament) {
        return departamentRepository.save(departament);
    }

    /**
     * department removing from data base by id value
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteDepo(Long id) {
        departamentRepository.deleteById(id);
    }

    /**
     * get list of departments which used in EmployeeController
     *
     * @return
     */
    @Override
    public Iterable<Departament> findListDepartment() {
        return departamentRepository.findAll();
    }


    public boolean isDepoPresent(Long id) {
        Departament depo = departamentRepository.findById(id).orElse(null);
        if (depo != null)
            return true;
        return false;
    }

}
