package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "impasto_ingrediente")
@Getter
@Setter
//@EqualsAndHashCode(exclude = {"ingrediente", "impasto"})
public class DoughIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "impastoingrediente_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingrediente_id")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "impasto_id")
    private Dough dough;

    @Column(name = "quantita")
    private Double quantity;

    @Column(name = "unita_di_misura")
    @Enumerated(EnumType.STRING)
    private MeasureUnit measureUnit;
}

