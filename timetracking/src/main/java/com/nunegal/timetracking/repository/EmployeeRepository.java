package com.nunegal.timetracking.repository;

import com.nunegal.timetracking.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeId(Long id);
    Optional<Employee> findByEmployeUsername(String username);
}
