/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.io.Serializable;

/**
 * Employee entity
 */
@Entity
@Table(name = "employee")
public class Employee {

    //autoincrement value id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //validations
    //value name
    @NotEmpty
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;

    //validations
    //value salary
    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name = "salary")
    private String salary;

    //validations
    //value email
    @NotEmpty
    @Email
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;

    //validations
    //value departmentId
    //relations between columns: reference ManyToOne
    //columns departament_id & id from different tables joining
    @NotEmpty
    @ManyToOne(optional = false)
    @JoinColumn(name = "departament_id", referencedColumnName = "id")
    private Departament departamentId;

    //default constructor
    public Employee() {
    }

    //employee constructor
    public Employee(@NotEmpty @Size(min = 1, max = 50) String name,
                    @NotEmpty @Size(min = 3, max = 20) String salary,
                    @NotEmpty @Email @Size(min = 1, max = 50) String email,
                    @NotEmpty Departament departamentId) {
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.departamentId = departamentId;
    }
    //getters & setters for values
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Departament getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(Departament departamentId) {
        this.departamentId = departamentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", email='" + email + '\'' +
                ", departamentId=" + departamentId +
                '}';
    }
}
