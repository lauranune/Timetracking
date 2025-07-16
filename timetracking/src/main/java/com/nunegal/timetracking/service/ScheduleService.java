package com.nunegal.timetracking.service;

import com.nunegal.timetracking.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    public Schedule save(Schedule schedule);
    public List<Schedule> findAll();
    public Schedule findById(int id);
    public void deleteById(int id);
}
