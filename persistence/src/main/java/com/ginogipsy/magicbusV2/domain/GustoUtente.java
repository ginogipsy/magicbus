package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class GustoUtente extends Gusto {

    @Id
    @GeneratedValue(generator = "gustoutente_id")
    private Integer id;

    @Column(name = "inserita_da_utente")
    private Boolean inseritaDaUtente;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "username_inseritore")
    private String username;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "gusto_id")
    private Gusto gusto;

}

