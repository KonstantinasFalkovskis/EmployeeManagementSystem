package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
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

    public Departament() {
    }

    public Departament(@NotEmpty @Size(min = 3, max = 150) String departament, List<Employee> employee) {
        this.departament = departament;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Departament{" +
                "id=" + id +
                ", departament='" + departament + '\'' +
                ", employee=" + employee +
                '}';
    }
}
