package com.nunegal.timetracking.config;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.entity.Department;
import com.nunegal.timetracking.entity.Rol;
import com.nunegal.timetracking.entity.Schedule;
import com.nunegal.timetracking.repository.DepartmentRepository;
import com.nunegal.timetracking.repository.RolRepository;
import com.nunegal.timetracking.repository.ScheduleRepository;
import com.nunegal.timetracking.service.EmployeeServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EmployeeServiceImple employeeService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void run(String[] args) throws Exception {
        String username= "admin";
        String password= "1234";

        if(employeeService.existsByUsername(username)){
            System.out.println("El usuario ya existe");
            return;
        }

        Optional<Department> department= departmentRepository.findById(1);
        Optional <Rol> rol= rolRepository.findById(2);
        Optional <Schedule> schedule= scheduleRepository.findById(1);


        EmployeeDto dto= new EmployeeDto();
        dto.setUsername(username);
        dto.setPassword(employeeService.encryptPassword(password));
        dto.setEnabled(true);
        dto.setDepartmentId(department.get().getId());
        dto.setRolId(rol.get().getId());
        dto.setScheduleId(schedule.get().getId());

        employeeService.save(dto);

        System.out.println("El usuario se ha registrado");
    }
}