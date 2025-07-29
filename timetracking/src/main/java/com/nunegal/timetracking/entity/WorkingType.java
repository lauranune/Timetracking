package com.nunegal.timetracking.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "working_type")
public class WorkingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idworking_type")
    private int id;

    private String name;
    private String description;

    private LocalTime standardEntry;
    private LocalTime standardExit;

    private boolean hasPauseMorning;
    private LocalTime morningPauseStart;
    private LocalTime morningPauseEnd;

    private boolean hasLunchPause;
    private LocalTime lunchPauseStart;
    private LocalTime lunchPauseEnd;

    public WorkingType() {
    }

    public WorkingType(WorkingType workingType) {
        this.id = workingType.id;
        this.name = workingType.name;
        this.description = workingType.description;
        this.standardEntry = workingType.standardEntry;
        this.standardExit = workingType.standardExit;
        this.hasPauseMorning = workingType.hasPauseMorning;
        this.morningPauseStart = workingType.morningPauseStart;
        this.morningPauseEnd = workingType.morningPauseEnd;
        this.hasLunchPause = workingType.hasLunchPause;
        this.lunchPauseStart = workingType.lunchPauseStart;
        this.lunchPauseEnd = workingType.lunchPauseEnd;
    }

    public WorkingType(int id, String name, String description, LocalTime standardEntry, LocalTime standardExit,
                       boolean hasPauseMorning, LocalTime morningPauseStart, LocalTime morningPauseEnd, boolean hasLunchPause,
                       LocalTime lunchPauseStart, LocalTime lunchPauseEnd) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.standardEntry = standardEntry;
        this.standardExit = standardExit;
        this.hasPauseMorning = hasPauseMorning;
        this.morningPauseStart = morningPauseStart;
        this.morningPauseEnd = morningPauseEnd;
        this.hasLunchPause = hasLunchPause;
        this.lunchPauseStart = lunchPauseStart;
        this.lunchPauseEnd = lunchPauseEnd;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStandardEntry() {
        return standardEntry;
    }

    public void setStandardEntry(LocalTime standardEntry) {
        this.standardEntry = standardEntry;
    }

    public LocalTime getStandardExit() {
        return standardExit;
    }

    public void setStandardExit(LocalTime standardExit) {
        this.standardExit = standardExit;
    }

    public boolean isHasPauseMorning() {
        return hasPauseMorning;
    }

    public void setHasPauseMorning(boolean hasPauseMorning) {
        this.hasPauseMorning = hasPauseMorning;
    }

    public LocalTime getMorningPauseStart() {
        return morningPauseStart;
    }

    public void setMorningPauseStart(LocalTime morningPauseStart) {
        this.morningPauseStart = morningPauseStart;
    }

    public LocalTime getMorningPauseEnd() {
        return morningPauseEnd;
    }

    public void setMorningPauseEnd(LocalTime morningPauseEnd) {
        this.morningPauseEnd = morningPauseEnd;
    }

    public boolean isHasLunchPause() {
        return hasLunchPause;
    }

    public void setHasLunchPause(boolean hasLunchPause) {
        this.hasLunchPause = hasLunchPause;
    }

    public LocalTime getLunchPauseStart() {
        return lunchPauseStart;
    }

    public void setLunchPauseStart(LocalTime lunchPauseStart) {
        this.lunchPauseStart = lunchPauseStart;
    }

    public LocalTime getLunchPauseEnd() {
        return lunchPauseEnd;
    }

    public void setLunchPauseEnd(LocalTime lunchPauseEnd) {
        this.lunchPauseEnd = lunchPauseEnd;
    }
}