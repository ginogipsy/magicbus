package com.ginogipsy.magicbusV2.domain;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Table(name = "alcolico")
public class Alcolico extends Bibita{

    @Column(name = "grado_alcolico")
    private double gradoAlcolico;
}

