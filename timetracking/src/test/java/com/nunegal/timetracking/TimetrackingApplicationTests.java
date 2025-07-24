package com.nunegal.timetracking;

import com.nunegal.timetracking.entity.Employee;
import com.nunegal.timetracking.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TimetrackingApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void verifyDatabasePassword() {
        String username = "atilla";
        String expectedPassword = "1234";

        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow();

        boolean matches = passwordEncoder.matches(expectedPassword, employee.getPassword());

        System.out.println("Contraseña en BD: " + employee.getPassword());
        System.out.println("¿'" + expectedPassword + "' coincide ahora?: " + matches);

        assertTrue(matches);

        System.out.println(passwordEncoder.matches("1234", "$2a$10$BXY0BDZ8Jw4se.Es6ueqfuUtlp5q1Xu1MaQ2oRxiqpRfK5GTf3ody"));

    }
}
