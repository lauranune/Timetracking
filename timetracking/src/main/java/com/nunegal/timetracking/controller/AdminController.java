package com.nunegal.timetracking.controller;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.entity.Department;
import com.nunegal.timetracking.entity.Employee;
import com.nunegal.timetracking.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('admin')")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RolService rolService;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/index")
    public String index(Model model, Principal principal) {
        List<EmployeeDto> employeeList= employeeService.findAll();
        model.addAttribute("employees", employeeList);
        model.addAttribute("adminName", principal.getName());
        return "admin/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        employeeService.delete(id);
        return "redirect:/admin/index";
    }

    @GetMapping("/edit")
    public String edit(Model model,int id) {
        EmployeeDto employee= employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("department", departmentService.findAll());
        model.addAttribute("rol", rolService.findAll());
        model.addAttribute("schedule", scheduleService.findAll());
        return "modalEdit :: modalEdit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("employee") @Valid EmployeeDto employee, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("employee", employee);
            model.addAttribute("department", departmentService.findAll());
            model.addAttribute("rol", rolService.findAll());
            model.addAttribute("schedule", scheduleService.findAll());
            return "modalEdit :: modalEditAdmin";
        }
        employeeService.update(employee);
        return "redirect:/admin/index";
    }

}
