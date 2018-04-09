/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Department entity
 */
@Entity
@Table(name = "departament")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Departament.findAll", query = "SELECT c FROM Departament c")
        , @NamedQuery(name = "Departament.findByName", query = "SELECT c FROM Departament c WHERE c.departament = :departament")
        , @NamedQuery(name = "Departament.findById", query = "SELECT c FROM Departament c WHERE c.id = :id")})
public class Departament implements Serializable {

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

    //department constructor for testing
    public Departament(Long id, String departament) {
        this.id = id;
        this.departament = departament;
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
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departament)) {
            return false;
        }
        Departament other = (Departament) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "Departament{" +
//                "id=" + id +
//                ", departament='" + departament + '\'' +
//                ", employee=" + employee +
//                '}';
//    }
}
