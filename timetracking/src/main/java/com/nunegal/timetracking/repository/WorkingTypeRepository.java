package com.nunegal.timetracking.repository;

import com.nunegal.timetracking.entity.WorkingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingTypeRepository extends JpaRepository<WorkingType, Long> {
}
