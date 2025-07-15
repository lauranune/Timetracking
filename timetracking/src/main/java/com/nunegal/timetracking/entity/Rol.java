package com.nunegal.timetracking.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idrol")
    private int id;

    private String type;

    private String description;

    public Rol() {}
    public Rol(int id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
