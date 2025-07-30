package com.nunegal.timetracking.service;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.dto.ScheduleDto;
import com.nunegal.timetracking.entity.*;
import com.nunegal.timetracking.mapper.EmployeeMapper;
import com.nunegal.timetracking.mapper.ScheduleMapper;
import com.nunegal.timetracking.repository.*;
import jakarta.transaction.Transactional;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImple implements EmployeeService, UserDetailsService {
    @Value("${app.default.password:1234}")
    private String defaultPassword;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private WorkingTypeRepository workingTypeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Autenticación para Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        if (employee.getRol() == null) {
            throw new IllegalStateException("El usuario no tiene rol asignado: " + username);
        }
        return User.builder()
                .username(employee.getUsername())
                .password(employee.getPassword())
                .disabled(!employee.isEnabled())
                .accountExpired(false)
                .credentialsExpired(false)
                .accountLocked(false)
                .authorities("ROLE_" + employee.getRol().getType().toLowerCase())
                .build();
    }

    @Override
    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto) {
        try {
            if (existsByUsername(employeeDto.getUsername())) {
                throw new RuntimeException("El usuario ya existe");
            }
            if (employeeDto.getPassword() == null || employeeDto.getPassword().isEmpty()) {
                employeeDto.setPassword(passwordEncoder.encode(defaultPassword));
            } else {
                employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
            }

            employeeDto.setEnabled(true);

            Employee employee = employeeMapper.toEntity(employeeDto);
            employeeRepository.save(employee);

            return employeeMapper.toEmployeeDto(employee);
        } catch (Exception e) {
            System.err.println("Error al guardar empleado: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee existing = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
        Rol rol = rolRepository.findById(employeeDto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        WorkingType workingType = workingTypeRepository.findById(employeeDto.getWorkingTypeId())
                .orElseThrow(() -> new RuntimeException("Jornada no encontrada"));

        existing.setName(employeeDto.getName());
        existing.setSurname(employeeDto.getSurname());
        existing.setDepartment(department);
        existing.setRol(rol);
        existing.setWorkingType(workingType);
        existing.setEnabled(employeeDto.isEnabled());

        if (StringUtils.hasText(employeeDto.getPassword())) {
            existing.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        }

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

    @Override
    public void updateSchedule(int employeeId, ScheduleDto scheduleDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Schedule schedule = scheduleRepository.findByEmployeeIdAndDate(employeeId, scheduleDto.getDate());

        if (schedule == null) {
            schedule = scheduleMapper.toEntity(scheduleDto);
            schedule.setEmployee(employee);
        } else {
            schedule.setEntry(scheduleDto.getEntry());
            schedule.setExit(scheduleDto.getExit());
            schedule.setPauseEntry(scheduleDto.getPauseEntry());
            schedule.setPauseExit(scheduleDto.getPauseExit());
            schedule.setLunchEntry(scheduleDto.getLunchEntry());
            schedule.setLunchExit(scheduleDto.getLunchExit());
        }

        scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleDto findTodaySchedule(int employeeId, LocalDate today) {
        Schedule schedule = scheduleRepository.findByEmployeeIdAndDate(employeeId, today);
        return scheduleMapper.toScheduleDto(schedule);
    }

    @Override
    public ScheduleDto createSchedule(int employeeId, ScheduleDto scheduleDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Schedule schedule = new Schedule();
        schedule.setEmployee(employee);
        schedule.setDate(scheduleDto.getDate());
        schedule.setEntry(scheduleDto.getEntry());
        schedule.setExit(scheduleDto.getExit());
        schedule.setPauseEntry(scheduleDto.getPauseEntry());
        schedule.setPauseExit(scheduleDto.getPauseExit());
        schedule.setLunchEntry(scheduleDto.getLunchEntry());
        schedule.setLunchExit(scheduleDto.getLunchExit());
        schedule.setWorkingType(employee.getWorkingType());

        if (scheduleDto.getWorkingTypeId() != 0) {
            WorkingType workingType = workingTypeRepository.findById(scheduleDto.getWorkingTypeId())
                    .orElseThrow(() -> new RuntimeException("Tipo de jornada no encontrado"));
            schedule.setWorkingType(workingType);
        }

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toScheduleDto(savedSchedule);
    }
}