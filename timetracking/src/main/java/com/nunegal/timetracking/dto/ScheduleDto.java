package com.nunegal.timetracking.dto;

import com.nunegal.timetracking.entity.Schedule;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleDto implements Serializable {
    private int id;
    private LocalDate date;

    private LocalTime pauseEntry;
    private LocalTime pauseExit;

    private LocalTime lunchEntry;
    private LocalTime lunchExit;

    private LocalTime exit;

    private int workingTypeId;
    private String workingTypeName;

    public ScheduleDto() {
    }

    public ScheduleDto(Schedule schedule) {
        this.id = schedule.getId();
        this.date = schedule.getDate();
        this.pauseEntry = schedule.getPauseEntry();
        this.pauseExit = schedule.getPauseExit();
        this.lunchEntry = schedule.getLunchEntry();
        this.lunchExit = schedule.getLunchExit();
        this.exit = schedule.getExit();
        this.workingTypeId = schedule.getWorkingType().getId();
        this.workingTypeName = schedule.getWorkingType().getName();

    }
}
