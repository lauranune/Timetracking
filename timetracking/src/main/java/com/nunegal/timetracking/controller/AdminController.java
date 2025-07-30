package com.nunegal.timetracking.controller;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    private WorkingTypeService workingTypeService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/index")
    public String index(Model model, Principal principal) {
        List<EmployeeDto> employeeList = employeeService.findAll();
        model.addAttribute("employees", employeeList);
        model.addAttribute("adminName", principal.getName());
        return "admin/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
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
        model.addAttribute("workingTypes", workingTypeService.findAll());
        return "admin/modalEdit :: modalEditAdmin";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("employee") @Valid EmployeeDto employee, Model model, BindingResult result) {

        if (StringUtils.hasText(employee.getPassword())) {
            if (!employee.getPassword().equals(employee.getConfirmPassword())) {
                result.rejectValue("confirmPassword", "error.employee", "Las contraseñas no coinciden");
            }
        } else {
            employee.setPassword(null);
        }

        if (result.hasErrors()) {
            model.addAttribute("employee", employee);
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("roles", rolService.findAll());
            model.addAttribute("workingTypes", workingTypeService.findAll());
            return "admin/modalEdit :: modalEditAdmin";
        }
        EmployeeDto updated = employeeService.update(employee);
        System.out.println("Datos actualizados: " + updated.toString());
        return "redirect:/admin/index";
    }

    @GetMapping("/formNewUser")
    public String formNewUser(Model model) {
        model.addAttribute("employee", new EmployeeDto());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("roles", rolService.findAll());
        model.addAttribute("workingTypes", workingTypeService.findAll());
        return "admin/formNewUser :: content";
    }

    @PostMapping("/new")
    public String newEmployee(@ModelAttribute("employee") @Valid EmployeeDto employee, BindingResult result, Model model) {
        if (!employee.isPasswordConfirmed()) {
            result.rejectValue("confirmPassword", "error.employee", "Las contraseñas no coinciden");
        }

        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            model.addAttribute("roles", rolService.findAll());
            model.addAttribute("workingTypes", workingTypeService.findAll());
            return "admin/formNewUser :: content";
        }
        if (employee.getUsername() == null || employee.getUsername().isEmpty()) {
            employee.setUsername(employeeService.generateUniqueUsername(
                    employee.getName(),
                    employee.getSurname()
            ));
        }

        employee.setEnabled(true);
        employeeService.save(employee);
        return "redirect:/admin/index";
    }
}