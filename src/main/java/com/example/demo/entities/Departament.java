package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class Departament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "departament")
    private String departament;
}
