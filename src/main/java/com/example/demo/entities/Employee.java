package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;

    @Size(min = 3, max = 20)
    @Column(name = "salary")
    private String salary;

    @Email
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;

    @Column(name = "departamentId")
    private Departament departamentId;


}
