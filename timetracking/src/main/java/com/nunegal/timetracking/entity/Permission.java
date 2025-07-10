package com.nunegal.timetracking.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpermission")
    private Long id;

    private LocalDate date;
    private LocalTime entry;
    private LocalTime exit;
    private String description;

    @ManyToOne
    @JoinColumn(name="employee_iduser")
    private Employee employee;

    public Permission() {}

    public Permission(Long id, LocalDate date, LocalTime entry, LocalTime exit, String description) {
        this.id = id;
        this.date = date;
        this.entry = entry;
        this.exit = exit;
        this.description = description;
    }

    public Long getId() {return id; }
    public void setId(Long idn) {this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getEntry() { return entry; }
    public void setEntry(LocalTime entry) { this.entry = entry; }
    public LocalTime getExit() { return exit; }
    public void setExit(LocalTime exit) { this.exit = exit; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}