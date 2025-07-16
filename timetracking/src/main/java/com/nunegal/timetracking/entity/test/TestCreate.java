package com.nunegal.timetracking.entity.test;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("tipoCreacion")
public class TestCreate extends Test{

    private int edad;
}
