package com.nunegal.timetracking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idschedule")
    private int id;

    private LocalDate date;

    private LocalTime entry;

    private LocalTime pauseEntry;
    private LocalTime pauseExit;

    private LocalTime lunchEntry;
    private LocalTime lunchExit;

    private LocalTime exit;

    @ManyToOne
    @JoinColumn(name = "working_type_idworking_type")
    private WorkingType workingType;

    public Schedule() {
    }

    public Schedule(int id, LocalDate date, LocalTime entry, LocalTime pauseEntry, LocalTime pauseExit, LocalTime lunchEntry, LocalTime lunchExit, LocalTime exit, WorkingType workingType) {
        this.id = id;
        this.date = date;
        this.entry = entry;
        this.pauseEntry = pauseEntry;
        this.pauseExit = pauseExit;
        this.lunchEntry = lunchEntry;
        this.lunchExit = lunchExit;
        this.exit = exit;
        this.workingType = workingType;
    }
}