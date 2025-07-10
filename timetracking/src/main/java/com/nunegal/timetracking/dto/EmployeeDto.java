package com.nunegal.timetracking.dto;

import java.io.Serializable;

import com.nunegal.timetracking.entity.Employee;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeDto implements Serializable {
    private Long id;

   @NotEmpty(message="El nombre no debe estar vacío")
   @Size(min=2, max=50)
   @Pattern(regexp = "^[\\p{L} ]+$", message = "El nombre no puede contener números")
   private String name;

   @NotEmpty(message="El apellido no debe estar vacío")
   @Size(min=2, max=50)
   @Pattern(regexp = "^[\\p{L} ]+$", message = "El apellido no puede contener números")
   private String surname;

    private String username;
    private String password;

    @NotNull(message = "El departamento es obligatorio")
    private Long departamentoId;

    private String departamentName;

    @NotNull(message = "El rol es obligatorio")
    private Long rolId;

    private String rolName;

    public EmployeeDto() {}

    public EmployeeDto(Employee employee) {}

   //Constructor para crear nuevos empleados

   public EmployeeDto(String name, String surname, String username, String password, Long departamentoId, Long rolId) {
       this.name = name;
       this.surname = surname;
       this.username = username;
       this.password = password;
       this.departamentoId = departamentoId;
       this.rolId = rolId;
   }

   //Constructor completo para convertir entidad

   public EmployeeDto(Long id, String name, String surname, String username, String password,
                      Long departamentoId, String departamentName, Long rolId, String rolName) {
       this.id = id;
       this.name = name;
       this.surname = surname;
       this.username = username;
       this.password = password;
       this.departamentoId = departamentoId;
       this.departamentName = departamentName;
       this.rolId = rolId;
       this.rolName = rolName;
   }

   public Long getId() {return id;}
   public void setId(Long id) {this.id = id;}
   public String getName() {return name;}
   public void setName(String name) {this.name = name;}
   public String getSurname() {return surname;}
   public void setSurname(String surname) {this.surname = surname;}
   public String getUsername() {return username;}
   public void setUsername(String username) {this.username = username;}
   public String getPassword() {return password;}
   public void setPassword(String password) {this.password = password;}
   public Long getDepartamentoId() {return departamentoId;}
   public void setDepartamentoId(Long departamentoId) {this.departamentoId = departamentoId;}
   public String getDepartamentName() {return departamentName;}
   public void setDepartamentName(String departamentName) {this.departamentName = departamentName;}
   public Long getRolId() {return rolId;}
   public void setRolId(Long rolId) {this.rolId = rolId;}
   public String getRolName() {return rolName;}
   public void setRolName(String rolName) {this.rolName = rolName;}

}