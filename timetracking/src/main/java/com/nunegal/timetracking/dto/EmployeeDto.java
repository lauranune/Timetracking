package com.nunegal.timetracking.dto;

import com.nunegal.timetracking.entity.Employee;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

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

    private int scheduleId;

    private String scheduleName;


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
        this.scheduleId = employee.getSchedule().getId();
        this.scheduleName = employee.getSchedule().getWorkingType().getName();
    }


    //Constructor para crear nuevos empleados

    public EmployeeDto(String name, String surname, String username, boolean enabled,
                       String password, int departmentId, int rolId, int scheduleId) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.enabled = enabled;
        this.password = password;
        this.departmentId = departmentId;
        this.rolId = rolId;
        this.scheduleId = scheduleId;
    }

    //Constructor completo para convertir entidad

    public EmployeeDto(int id, String name, String surname, String username, String password,
                       boolean enabled, int departmentId, String departmentName, int rolId, String rolType,
                       int scheduleId, String scheduleName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.rolId = rolId;
        this.rolType = rolType;
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
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

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String name) {
        this.scheduleName = name;
    }

    public boolean isPasswordConfirmed() {
        return password != null && !password.equals(confirmPassword);
    }
}