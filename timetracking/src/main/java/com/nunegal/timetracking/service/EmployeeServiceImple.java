package com.nunegal.timetracking.service;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.entity.Department;
import com.nunegal.timetracking.entity.Employee;
import com.nunegal.timetracking.entity.Rol;
import com.nunegal.timetracking.entity.Schedule;
import com.nunegal.timetracking.mapper.EmployeeMapper;
import com.nunegal.timetracking.repository.DepartmentRepository;
import com.nunegal.timetracking.repository.EmployeeRepository;
import com.nunegal.timetracking.repository.RolRepository;
import com.nunegal.timetracking.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImple implements EmployeeService, UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeMapper employeeMapper;


    // Autenticación para Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String role = "USER";
        if (employee.getRol() != null && employee.getRol().getType() != null) {
            role = employee.getRol().getType();
        }
        return User.builder()
                .username(employee.getUsername())
                .password(employee.getPassword())
                .disabled(!employee.isEnabled())
                .accountExpired(false)
                .credentialsExpired(false)
                .accountLocked(false)
                .authorities("ROLE_" + role)
                .build();
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        if (existsByUsername(employeeDto.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }
        employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        Employee employee = employeeMapper.toEntity(employeeDto);
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeDto(employee);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee existing = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
        Rol rol = rolRepository.findById(employeeDto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        Schedule schedule = scheduleRepository.findById(employeeDto.getScheduleId())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        existing.setName(employeeDto.getName());
        existing.setSurname(employeeDto.getSurname());
        existing.setDepartment(department);
        existing.setRol(rol);
        existing.setSchedule(schedule);

        employeeMapper.updateEmployee(existing, employeeDto);

        Employee updated = employeeRepository.save(existing);
        return employeeMapper.toEmployeeDto(updated);
    }

    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(Integer id) {
        return employeeRepository.findById(id)
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

    @Override
    public String generateUniqueUsername(String name, String surname) {
        String normalizedName = Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase();
        String normalizedSurname = Normalizer.normalize(surname, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase();

        return (normalizedName.charAt(0) + normalizedSurname)
                .replaceAll("\\s+", "");
    }
}
