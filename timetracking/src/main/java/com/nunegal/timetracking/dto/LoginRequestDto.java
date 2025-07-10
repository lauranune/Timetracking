package com.nunegal.timetracking.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {

    @NotBlank(message="El usuario no debe estar vacío")
    private String username;

    @NotBlank(message="La contraseña no debe estar vacía")
    private String password;

    public LoginRequestDto() {}

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
}
