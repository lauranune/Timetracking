package com.nunegal.timetracking.repository;

import com.nunegal.timetracking.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
