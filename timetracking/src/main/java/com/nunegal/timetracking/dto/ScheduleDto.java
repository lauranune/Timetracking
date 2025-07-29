package com.nunegal.timetracking.dto;

import com.nunegal.timetracking.entity.Schedule;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDto implements Serializable {
    private int id;
    private LocalDate date;

    private LocalTime entry;

    private LocalTime pauseEntry;
    private LocalTime pauseExit;

    private LocalTime lunchEntry;
    private LocalTime lunchExit;

    private LocalTime exit;

    private int workingTypeId;
    private String workingTypeName;

    private int employeeId;

    public ScheduleDto() {
    }

    public ScheduleDto(Schedule schedule) {
        this.id = schedule.getId();
        this.date = schedule.getDate();
        this.entry = schedule.getEntry();
        this.pauseEntry = schedule.getPauseEntry();
        this.pauseExit = schedule.getPauseExit();
        this.lunchEntry = schedule.getLunchEntry();
        this.lunchExit = schedule.getLunchExit();
        this.exit = schedule.getExit();
        this.workingTypeId = schedule.getWorkingType().getId();
        this.workingTypeName = schedule.getWorkingType().getName();
        this.employeeId = schedule.getEmployee().getId();

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

    public int getWorkingTypeId() {
        return workingTypeId;
    }

    public void setWorkingTypeId(int workingTypeId) {
        this.workingTypeId = workingTypeId;
    }

    public String getWorkingTypeName() {
        return workingTypeName;
    }

    public void setWorkingTypeName(String workingTypeName) {
        this.workingTypeName = workingTypeName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}