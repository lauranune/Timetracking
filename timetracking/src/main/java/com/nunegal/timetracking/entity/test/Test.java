package com.nunegal.timetracking.entity.test;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorColumn(name="TestType", discriminatorType = DiscriminatorType.STRING)
public abstract class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String name;



}
