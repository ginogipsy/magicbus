package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fritto_ingrediente")
@Getter
@Setter
//@EqualsAndHashCode(exclude = {"fritto","ingrediente"})
public class FriedIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "frittoingrediente_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fritto_id")
    private Fried fried;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingrediente_id")
    private Ingredient ingredient;

    @Column(name = "quantita")
    private Double quantity;

    @Column(name = "unita_di_misura")
    @Enumerated(EnumType.STRING)
    private MeasureUnit measureUnit;
}