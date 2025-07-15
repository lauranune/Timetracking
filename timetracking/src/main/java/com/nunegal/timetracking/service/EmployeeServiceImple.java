package com.nunegal.timetracking.service;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.entity.Employee;
import com.nunegal.timetracking.mapper.EmployeeMapper;
import com.nunegal.timetracking.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImple implements EmployeeService, UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeMapper employeeMapper;

    // Autenticación para Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        if (employee.getRol() == null) {
            System.out.println(" El empleado no tiene un rol asignado.");
        } else {
            System.out.println(" Rol ID: " + employee.getRol().getId());
            System.out.println(" Rol TYPE: " + employee.getRol().getType());
        }
        return new User(
                employee.getUsername(),
                employee.getPassword(),
                employee.isEnabled(),
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("ROLE_" + employee.getRol().getType()))
        );
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        if (existsByUsername(employeeDto.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }
        Employee employee= employeeMapper.toEntity(employeeDto);
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeDto(employee);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        if (!employeeRepository.existsById(employeeDto.getId())) {
            throw new RuntimeException("Empleado no encontrado");
        }
        employeeDto.setPassword(encryptPassword(employeeDto.getPassword()));
        Employee updated = employeeRepository.save(employeeMapper.toEntity(employeeDto));
        return employeeMapper.toEmployeeDto(updated);
    }

    @Override
    public void delete(int id) {
        employeeRepository.deleteById((int) id);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(int id) {
        return employeeRepository.findById((int) id)
                .map(employeeMapper::toEmployeeDto)
                .orElse(null);
    }

    @Override
    public EmployeeDto findByUsername(String username) {
        return employeeRepository.findByUsername(username)
                .map(employeeMapper::toEmployeeDto)
                .orElse(null);
    }

    @Override
    public boolean existsByUsername(String username) {
        return employeeRepository.findByUsername(username).isPresent();
    }

    @Override
    public String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}