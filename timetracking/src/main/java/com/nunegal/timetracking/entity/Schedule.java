package com.nunegal.timetracking.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idschedule")
    private int id;

    private LocalDate date;

    private LocalTime entry;

    private LocalTime pause_entry;
    private LocalTime pause_exit;

    private LocalTime lunch_entry;
    private LocalTime lunch_exit;

    private LocalTime exit;

    @ManyToOne
    @JoinColumn(name="working_type_idworking_type")
    private WorkingType workingType;

    public Schedule() {}

    public Schedule (int id, LocalDate date, LocalTime entry, LocalTime pause_entry, LocalTime pause_exit, LocalTime lunch_entry, LocalTime lunch_exit, LocalTime exit) {
        this.id = id;
        this.date = date;
        this.entry = entry;
        this.pause_entry = pause_entry;
        this.pause_exit = pause_exit;
        this.lunch_entry = lunch_entry;
        this.lunch_exit = lunch_exit;
        this.exit = exit;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}
    public LocalTime getEntry() {return entry;}
    public void setEntry(LocalTime entry) {this.entry = entry;}
    public LocalTime getPause_entry() {return pause_entry;}
    public void setPause_entry(LocalTime pause_entry) {this.pause_entry = pause_entry;}
    public LocalTime getPause_exit() {return pause_exit;}
    public void setLunch_entry(LocalTime lunch_entry) {this.lunch_entry = lunch_entry;}
    public LocalTime getLunch_exit() {return lunch_exit;}
    public void setExit(LocalTime exit) {this.exit = exit;}
    public LocalTime getExit() {return exit;}
    public void setWorkingType(WorkingType workingType) {this.workingType = workingType;}
    public WorkingType getWorkingType() {return workingType;}
}