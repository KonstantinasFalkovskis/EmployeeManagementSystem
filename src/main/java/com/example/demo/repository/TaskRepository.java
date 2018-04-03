/**
 * @Author - Falco Constantine
 * @date - 2018.04.02
 * @version - v.1.0
 */
package com.example.demo.repository;

import com.example.demo.entities.Task;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Task repository
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

 /**
  * Searching tasks by user
  * @param user
  * @return
  */
 List<Task> findByUser(User user);

}
