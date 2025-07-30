package com.nunegal.timetracking.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpermission")
    private int id;

    private LocalDate perDate;
    private LocalTime perEntry;
    private LocalTime perExit;
    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_iduser")
    private Employee employee;

    public Permission() {
    }

    public Permission(int id, LocalDate date, LocalTime perEntry, LocalTime perExit, String description) {
        this.id = id;
        this.perDate = date;
        this.perEntry = perEntry;
        this.perExit = perExit;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getPerDate() {
        return perDate;
    }

    public void setPerDate(LocalDate perDate) {
        this.perDate = perDate;
    }

    public LocalTime getPerEntry() {
        return perEntry;
    }

    public void setPerEntry(LocalTime entry) {
        this.perEntry = entry;
    }

    public LocalTime getPerExit() {
        return perExit;
    }

    public void setPerExit(LocalTime exit) {
        this.perExit = exit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}