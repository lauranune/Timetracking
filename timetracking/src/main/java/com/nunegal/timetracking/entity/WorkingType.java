package com.nunegal.timetracking.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "working_type")
public class WorkingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idworking_type")
    private Long id;

    private String name;
    private String description;

    public WorkingType() {}

    public WorkingType(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}