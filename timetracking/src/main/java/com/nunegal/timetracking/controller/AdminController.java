package com.nunegal.timetracking.controller;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.service.DepartmentService;
import com.nunegal.timetracking.service.EmployeeService;
import com.nunegal.timetracking.service.RolService;
import com.nunegal.timetracking.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${app.default.password}")
    private String defaultEmployeePassword;

    @GetMapping("/index")
    public String index(Model model, Principal principal) {
        List<EmployeeDto> employeeList = employeeService.findAll();
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
    public String editView(Model model, @RequestParam Integer id) {
        EmployeeDto employee = employeeService.findById(id);

        if (employee == null) {
            employee = new EmployeeDto();
        }

        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("roles", rolService.findAll());
        model.addAttribute("schedules", scheduleService.findAll());
        return "admin/modalEdit :: modalEditAdmin";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("employee") @Valid EmployeeDto employee, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("employee", employee);
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("roles", rolService.findAll());
            model.addAttribute("schedules", scheduleService.findAll());
            return "admin/modalEdit :: modalEditAdmin";
        }
        employeeService.update(employee);
        return "redirect:/admin/index";
    }

    @GetMapping("/formNewUser")
    public String formNewUser(Model model) {
        model.addAttribute("employee", new EmployeeDto());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("roles", rolService.findAll());
        model.addAttribute("schedules", scheduleService.findAll());
        model.addAttribute("defaultPassword", defaultEmployeePassword);
        return "admin/formNewUser :: content";
    }

    @PostMapping("/new")
    public String newEmployee(@ModelAttribute("employee") @Valid EmployeeDto employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("roles", rolService.findAll());
            model.addAttribute("schedules", scheduleService.findAll());
            return "admin/formNewUser :: content";
        }
        if (employee.getUsername() == null || employee.getUsername().isEmpty()) {
            employee.setUsername(employeeService.generateUniqueUsername(
                    employee.getName(),
                    employee.getSurname()
            ));
        }
        if (employee.getPassword() == null || employee.getPassword().isEmpty()) {
            employee.setPassword(passwordEncoder.encode(defaultEmployeePassword));
        }
        employee.setEnabled(true);
        employeeService.save(employee);
        return "redirect:/admin/index";
    }
}