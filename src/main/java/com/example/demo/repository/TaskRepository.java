package com.example.demo.repository;

import com.example.demo.entities.Task;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

 List<Task> findByUser(User user);

}
