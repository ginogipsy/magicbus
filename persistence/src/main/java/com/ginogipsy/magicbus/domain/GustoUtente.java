package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(exclude = {"gusto"})
@Data
@Entity(name = "gusto_utente")
public class GustoUtente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gustoutente_id")
    private Integer id;

    @Column(name = "inserita_da_utente", columnDefinition = "TINYINT", length = 1)
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

