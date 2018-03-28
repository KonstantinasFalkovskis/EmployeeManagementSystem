package com.example.demo.services;

import com.example.demo.entities.Departament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DepartamentService {

    //get department by name
    public Page<Departament> findDepoByName(String name, Pageable pageable);

    //get exist deprtaments list
    public Page<Departament> findAll(Pageable pageable);

    //get department info by id
    public Departament findOne(Long id);

    //save department into DB
    public Departament saveDepo(Departament departament);

    //remove department from data base
    public void deleteDepo(Long id);


}
