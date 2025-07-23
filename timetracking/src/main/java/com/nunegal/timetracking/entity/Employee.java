package com.nunegal.timetracking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private int id;

    private String name;
    private String surname;

    private String username;
    private String password;
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "department_iddepartment")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "rol_idrol")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "schedule_idschedule")
    private Schedule schedule;

    public Employee() {
    }

    //Constructor de pruebas
    public Employee(String username, String password, boolean enabled, Department department, Rol rol,
                    Schedule schedule) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.department = department;
        this.rol = rol;
        this.schedule = schedule;
    }

    public Employee(int id, String name, String surname, String username, String password, boolean enabled,
                    Department department, Rol rol, Schedule schedule) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.department = department;
        this.rol = rol;
        this.schedule = schedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int iduser) {
        this.id = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Employee [iduser=" + id + ", name=" + name + ", surname="
                + surname + ", user=" + username + ", password=" + password + ", department="
                + department + ", rol=" + rol + "]";
    }
}