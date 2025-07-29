package com.nunegal.timetracking.repository;

import com.nunegal.timetracking.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findByEmployeeIdAndDate(int employeeId, LocalDate date);
}
