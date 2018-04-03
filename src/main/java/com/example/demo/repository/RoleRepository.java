/**
 * @Author - Falco Constantine
 * @date - 2018.04.02
 * @version - v.1.0
 */
package com.example.demo.repository;

import com.example.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Roles repository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    /**
     * Searching roles by role
     * @param role
     * @return
     */
    List<Role> findByRole(Role role);
}
