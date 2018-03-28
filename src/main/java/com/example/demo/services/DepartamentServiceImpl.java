package com.example.demo.services;

import com.example.demo.entities.Departament;
import com.example.demo.repository.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class DepartamentServiceImpl implements DepartamentService {

    @Autowired
    private DepartamentRepository departamentRepository;

    @Override
    public Page<Departament> findDepoByName(String name, Pageable pageable) {
        return departamentRepository.findDepoByName(name, pageable);
    }

    @Override
    public Page<Departament> findAll(Pageable pageable) {
        return departamentRepository.findAll(pageable);
    }

    @Override
    public Departament findOne(Long id) {
        return departamentRepository.findById(id).orElse(null);
    }

    @Override
    public Departament saveDepo(Departament departament) {
        return departamentRepository.save(departament);
    }

    @Override
    public void deleteDepo(Long id) {
        departamentRepository.deleteById(id);
    }
}
