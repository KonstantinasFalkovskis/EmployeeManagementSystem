/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Department entity
 */
@Entity
public class Departament {

    //autoincrement value id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //validations
    //value departament
    @NotEmpty
    @Size(min = 3, max = 150)
    @Column(name = "departament")
    private String departament;

    //value employee -> type List<>
    //relations between columns: reference OneToMany -> in this case many employees works only in one department
    //reference to departamentId value from Employees class, cascade type = All
    @OneToMany(mappedBy = "departamentId", cascade = CascadeType.ALL)
    private List<Employee> employee;

    //default constructor
    public Departament() {
    }

    //department constructor
    public Departament(@NotEmpty @Size(min = 3, max = 150) String departament, List<Employee> employee) {
        this.departament = departament;
        this.employee = employee;
    }

    //getters and setters
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
