/**
 * @Author - Falco Constantine
 * @date - 2018.04.02
 * @version - v.1.0
 */
package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Users repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * searching by name + pagination
     * @param name
     * @param pageable
     * @return
     */
    Page<User> findByNameLike(String name, Pageable pageable);
    List<User> findByNameLike(String name);
}
