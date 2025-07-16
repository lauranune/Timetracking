package com.nunegal.timetracking.entity.test;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("tipoModificacion")
public class Testmod extends Test{

    private String direccion;
}
