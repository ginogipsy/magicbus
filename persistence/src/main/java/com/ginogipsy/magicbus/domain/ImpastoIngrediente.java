package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
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
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "impasto_id")
    private Impasto impasto;

    @Column(name = "quantita")
    private Double quantita;

    @Column(name = "unita_di_misura")
    @Enumerated(EnumType.STRING)
    private MeasureUnit measureUnit;
}

