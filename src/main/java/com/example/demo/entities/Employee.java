package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Size(min = 3, max = 20)
    @Column(name = "salary")
    private String salary;

    @NotEmpty
    @Email
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;

    @NotEmpty
    @JoinColumn(name = "departamentId", referencedColumnName = "id")
    @ManyToOne
    private Departament departamentId;


}
