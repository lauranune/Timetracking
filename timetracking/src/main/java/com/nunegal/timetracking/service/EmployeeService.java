package com.nunegal.timetracking.service;

import com.nunegal.timetracking.dto.EmployeeDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface EmployeeService {

    public EmployeeDto save(EmployeeDto employeeDto);
    public EmployeeDto update(EmployeeDto employeeDto);
    public void delete(int id);
    public List<EmployeeDto> findAll();
    public EmployeeDto findById(int id);
    boolean existsByUsername(String username);
    String encryptPassword(String rawPassword);
    EmployeeDto findByUsername(String username);
}
