package com.nunegal.timetracking.service;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.dto.ScheduleDto;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    public EmployeeDto save(EmployeeDto employeeDto);

    public EmployeeDto update(EmployeeDto employeeDto);

    public void delete(Integer id);

    public List<EmployeeDto> findAll();

    public EmployeeDto findById(Integer id);

    boolean existsByUsername(String username);

    String encryptPassword(String rawPassword);

    EmployeeDto findByUsername(String username);

    String generateUniqueUsername(String name, String surname);

    public void updateSchedule(int employeeId, ScheduleDto scheduleDto);

    ScheduleDto findTodaySchedule(int employeeId, LocalDate today);

    public ScheduleDto createSchedule(int employeeId, ScheduleDto scheduleDto);
}