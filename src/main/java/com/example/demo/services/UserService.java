package com.example.demo.services;

import com.example.demo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    Page<User> findAll(Pageable pageable);

    public User findOne(String email);

    public void createUser(User user);

    public void createAdmin(User user);

    public void deleteUser(String email);

    public User saveUser(User user);

    public List<User> listUsers();

    List<User> findByName(String name);

    Page<User> findByName(String name, Pageable pageable);
}
