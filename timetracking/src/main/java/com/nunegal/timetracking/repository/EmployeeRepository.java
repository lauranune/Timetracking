package com.nunegal.timetracking.repository;

import com.nunegal.timetracking.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findById(int id);
    Optional<Employee> findByUsername(String username);
}
