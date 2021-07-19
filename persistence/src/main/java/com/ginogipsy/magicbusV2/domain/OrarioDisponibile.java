package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "orario_disponibile")
@EqualsAndHashCode(exclude = {"id","ordini"})
public class OrarioDisponibile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orariodisponibile_id")
    private Integer id;

    @Column(name = "ora", nullable = false)
    private Integer ora;

    @Column(name = "minuti", nullable = false)
    private Integer minuti;

    @OneToMany(mappedBy = "orarioDisponibile")
    private Set<Ordine> ordini;
}
