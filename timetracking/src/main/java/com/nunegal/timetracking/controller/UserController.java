package com.nunegal.timetracking.controller;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.dto.ScheduleDto;
import com.nunegal.timetracking.service.EmployeeServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('user','admin')")
public class UserController {

    @Autowired
    private EmployeeServiceImple employeeService;

    @GetMapping("/index")
    public String index(Model model, Principal principal) {
        EmployeeDto employee = employeeService.findByUsername(principal.getName());
        ScheduleDto todaySchedule = employeeService.findTodaySchedule(employee.getId(), LocalDate.now());

        if (todaySchedule == null) {
            todaySchedule = new ScheduleDto();
        }

        boolean hasPause = employee.getWorkingTypeName() != null &&
                (employee.getWorkingTypeName().equalsIgnoreCase("full-time")
                        || employee.getWorkingTypeName().equalsIgnoreCase("intensive"));

        model.addAttribute("employee", employee);
        model.addAttribute("todaySchedule", todaySchedule);
        model.addAttribute("hasPause", hasPause);
        return "user/index";
    }


    @PostMapping("/clockin")
    public String clockin(@RequestParam String accion, Principal principal) {
        EmployeeDto employee = employeeService.findByUsername(principal.getName());
        ScheduleDto schedule = employeeService.findTodaySchedule(employee.getId(), LocalDate.now());
        LocalTime now = LocalTime.now();

        if (schedule == null && "entrada".equals(accion)) {
            schedule = new ScheduleDto();
            schedule.setDate(LocalDate.now());
            schedule.setEntry(now);
            employeeService.createSchedule(employee.getId(), schedule);
            return "redirect:/user/index";
        }

        if (schedule == null) {
            return "redirect:/user/index";
        }

        switch (accion) {
            case "entrada":
                if (schedule.getEntry() == null) {
                    schedule.setEntry(now);
                }
                break;
            case "salida":
                if (schedule.getExit() == null) {
                    schedule.setExit(now);
                }
                break;
            case "desayuno":
                if (schedule.getPauseEntry() == null) {
                    schedule.setPauseEntry(now);
                } else if (schedule.getPauseExit() == null) {
                    schedule.setPauseExit(now);
                }
                break;

            case "comida":
                if (schedule.getLunchEntry() == null) {
                    schedule.setLunchEntry(now);
                } else if (schedule.getLunchExit() == null) {
                    schedule.setLunchExit(now);
                }
                break;
        }
        employeeService.updateSchedule(employee.getId(), schedule);
        return "redirect:/user/index";
    }
}
