package com.nunegal.timetracking.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iduser")
    private Long id;

    private String name;
    private String surname;

    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name="department_iddepartment")
    private Department department;

    @ManyToOne
    @JoinColumn(name= "rol_idrol")
    private Rol rol;

    public Employee() {}

    public Employee(Long id, String name, String surname, String username, String password, Department department, Rol rol) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.department = department;
        this.rol = rol;
    }

    public Long getId() {return id;}
    public void setId(Long iduser) {this.id = iduser;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getSurname() {return surname;}
    public void setSurname(String surname) {this.surname = surname;}
    public String getUsername() {return username;}
    public void setUsername(String user) {this.username = user;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public Department getDepartment() {return department;}
    public void setDepartment(Department department) {this.department = department;}
    public Rol getRol() {return rol;}
    public void setRol(Rol rol) {this.rol = rol;}

    @Override
    public String toString() {
        return "Employee [iduser=" + id + ", name=" + name + ", surname="
                + surname + ", user=" + username + ", password=" + password + ", department="
                + department + ", rol=" + rol + "]";
    }
}
