package com.nunegal.timetracking.dto;

import com.nunegal.timetracking.entity.Employee;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


public class EmployeeDto implements Serializable {
    private int id;

    @NotEmpty(message = "El nombre no debe estar vacío")
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[\\p{L} ]+$", message = "El nombre no puede contener números")
    private String name;

    @NotEmpty(message = "El apellido no debe estar vacío")
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[\\p{L} ]+$", message = "El apellido no puede contener números")
    private String surname;

    private boolean enabled;

    private String username;
    @NotEmpty(message = "La contraseña es obligatoria")
    @Size(min = 4, max = 20, message = "La contraseña debe tener entre 4 y 20 caracteres")
    private String password;

    @Transient
    private String confirmPassword;

    @NotNull(message = "El departamento es obligatorio")
    private int departmentId;

    private String departmentName;

    @NotNull(message = "El rol es obligatorio")
    private int rolId;

    private String rolType;

    private int workingTypeId;

    private String workingTypeName;

    private List<ScheduleDto> schedules;


    public EmployeeDto() {
    }

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.username = employee.getUsername();
        this.password = employee.getPassword();
        this.enabled = employee.isEnabled();
        this.departmentId = employee.getDepartment().getId();
        this.departmentName = employee.getDepartment().getName();
        this.rolId = employee.getRol().getId();
        this.rolType = employee.getRol().getType();
        this.workingTypeName = employee.getWorkingType().getName();
        this.workingTypeId = employee.getWorkingType().getId();
        this.schedules = employee.getSchedules().stream().map(ScheduleDto::new).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getRolType() {
        return rolType;
    }

    public void setRolType(String rolType) {
        this.rolType = rolType;
    }

    public int getWorkingTypeId() {
        return workingTypeId;
    }

    public void setWorkingTypeId(int workingTypeId) {
        this.workingTypeId = workingTypeId;
    }

    public String getWorkingTypeName() {
        return workingTypeName;
    }

    public void setWorkingTypeName(String workingTypeName) {
        this.workingTypeName = workingTypeName;
    }

    public List<ScheduleDto> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleDto> schedules) {
        this.schedules = schedules;
    }

    public boolean isPasswordConfirmed() {
        return password != null && password.equals(confirmPassword);
    }
}