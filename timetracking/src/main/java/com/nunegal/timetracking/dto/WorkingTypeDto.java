package com.nunegal.timetracking.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WorkingTypeDto implements Serializable {

    private int id;
    private String name;
    private String description;

    public WorkingTypeDto() {
    }

    public WorkingTypeDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
