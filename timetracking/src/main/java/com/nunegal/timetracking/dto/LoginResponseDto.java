package com.nunegal.timetracking.dto;

public class LoginResponseDto {

    private String name;
    private String surname;
    private String rol;

    public LoginResponseDto() {}

    public LoginResponseDto(String name, String surname, String rol) {
        this.name = name;
        this.surname = surname;
        this.rol = rol;
    }
    public String getName() {return name;}
    public String getSurname() {return surname;}
    public String getRol() {return rol;}
    public void setName(String name) {this.name = name;}
    public void setSurname(String surname) {this.surname = surname;}
    public void setRol(String rol) {this.rol = rol;}
}