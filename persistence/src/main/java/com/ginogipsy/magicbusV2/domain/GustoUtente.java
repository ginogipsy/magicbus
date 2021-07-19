package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(exclude = {"utente"})
public class GustoUtente extends Gusto {

    @Column(name = "inserita_da_utente")
    private Boolean inseritaDaUtente;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "username_inseritore")
    private String username;
}

