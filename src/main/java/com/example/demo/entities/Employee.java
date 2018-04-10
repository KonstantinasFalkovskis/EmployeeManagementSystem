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
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * Employee entity
 */
@Entity
//@Table(name = "employee")
//@XmlRootElement
//@NamedQueries({@NamedQuery(name = "Employee.findAll", query = "SELECT a FROM Employee a")
//        , @NamedQuery(name = "Employee.findBySalary", query = "SELECT a FROM Employee a WHERE a.salary = :salary")
//        , @NamedQuery(name = "Employee.findByName", query = "SELECT a FROM Employee a WHERE a.name = :name")
//        , @NamedQuery(name = "Employee.findById", query = "SELECT a FROM Employee a WHERE a.id = :id")})
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
    //@NotEmpty
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
                    Departament departamentId) {
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.departamentId = departamentId;
    }
    //employee constructor for testing
    public Employee(Long id, String name, String salary, String email) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.email = email;
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

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Employee)) {
//            return false;
//        }
//        Employee other = (Employee) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

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

//    @Override
//    public String toString() {
//        return "Employee{" +
//                ", departamentId=" + departamentId +
//                '}';
//    }
}
