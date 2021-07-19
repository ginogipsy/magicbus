package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "impasto_ingrediente")
@Data
@EqualsAndHashCode(exclude = {"ingrediente", "impasto"})
public class ImpastoIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "impastoingrediente_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "impasto_id")
    private Impasto impasto;

    @Column(name = "quantita")
    private Double quantita;

    @Column(name = "unita_di_misura")
    @Enumerated(EnumType.STRING)
    private UnitaDiMisura unitaDiMisura;
}

