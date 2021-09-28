package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "gusto_ingrediente")
@Data
@EqualsAndHashCode(exclude = {"gusto", "ingrediente"})
public class GustoIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gustoingrediente_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gusto_id")
    private Gusto gusto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @Column(name = "quantita")
    private Double quantita;

    @Column(name = "unita_di_misura")
    @Enumerated(EnumType.STRING)
    private UnitaDiMisura unitaDiMisura;
}
