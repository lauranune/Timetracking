package com.nunegal.timetracking.controller;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.entity.Employee;
import com.nunegal.timetracking.service.EmployeeServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeServiceImple employeeService;

    @GetMapping("/index")
    public String index(Model model, Principal principal) {
        List<EmployeeDto> employeeList= employeeService.findAll();
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("username", principal.getName());
        return "admin/index";
    }


}
