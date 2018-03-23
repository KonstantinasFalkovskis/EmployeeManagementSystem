package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "departament")
public class Departament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Size(min = 3, max = 150)
    @Column(name = "departament")
    private String departament;

    @OneToMany(mappedBy = "departamentId", cascade = CascadeType.ALL)
    private List<Employee> employee;
}
