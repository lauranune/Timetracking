package com.nunegal.timetracking.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idschedule")
    private int id;

    @Column(name = "workDate")
    private LocalDate date;

    @Column(name = "entryTime")
    private LocalTime entry;

    private LocalTime pauseEntry;
    private LocalTime pauseExit;

    private LocalTime lunchEntry;
    private LocalTime lunchExit;

    @Column(name = "exitTime")
    private LocalTime exit;

    @ManyToOne
    @JoinColumn(name = "working_type_idworking_type")
    private WorkingType workingType;

    @ManyToOne
    @JoinColumn(name = "employee_iduser")
    private Employee employee;

    public Schedule() {
    }

    public Schedule(int id, LocalDate date, LocalTime entry, LocalTime pauseEntry, LocalTime pauseExit,
                    LocalTime lunchEntry, LocalTime lunchExit, LocalTime exit, WorkingType workingType
            , Employee employee) {
        this.id = id;
        this.date = date;
        this.entry = entry;
        this.pauseEntry = pauseEntry;
        this.pauseExit = pauseExit;
        this.lunchEntry = lunchEntry;
        this.lunchExit = lunchExit;
        this.exit = exit;
        this.workingType = workingType;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEntry() {
        return entry;
    }

    public void setEntry(LocalTime entry) {
        this.entry = entry;
    }

    public LocalTime getPauseEntry() {
        return pauseEntry;
    }

    public void setPauseEntry(LocalTime pauseEntry) {
        this.pauseEntry = pauseEntry;
    }

    public LocalTime getPauseExit() {
        return pauseExit;
    }

    public void setPauseExit(LocalTime pauseExit) {
        this.pauseExit = pauseExit;
    }

    public LocalTime getLunchEntry() {
        return lunchEntry;
    }

    public void setLunchEntry(LocalTime lunchEntry) {
        this.lunchEntry = lunchEntry;
    }

    public LocalTime getLunchExit() {
        return lunchExit;
    }

    public void setLunchExit(LocalTime lunchExit) {
        this.lunchExit = lunchExit;
    }

    public LocalTime getExit() {
        return exit;
    }

    public void setExit(LocalTime exit) {
        this.exit = exit;
    }

    public WorkingType getWorkingType() {
        return workingType;
    }

    public void setWorkingType(WorkingType workingType) {
        this.workingType = workingType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}