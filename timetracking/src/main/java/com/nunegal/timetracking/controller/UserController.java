package com.nunegal.timetracking.controller;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.entity.Employee;
import com.nunegal.timetracking.service.EmployeeServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('user')")
public class UserController {

    @Autowired
    private EmployeeServiceImple employeeService;

    @GetMapping("/index")
    public String index(Model model, Principal principal) {
        EmployeeDto employee = employeeService.findByUsername(principal.getName());
        model.addAttribute("employee", employee);
        return "user/index";
    }

    @GetMapping("/profile")
    public String welcome(@AuthenticationPrincipal Employee employee, Model model) {
        EmployeeDto user = employeeService.findByUsername(employee.getUsername());
        model.addAttribute("name", user.getName());
        model.addAttribute("surname", user.getSurname());
        return "user/profile";
    }


}
