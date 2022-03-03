package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "gusto_ingrediente")
@Getter
@Setter
//@EqualsAndHashCode(exclude = {"gusto", "ingrediente"})
public class ToppingIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gustoingrediente_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gusto_id")
    private Topping topping;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingrediente_id")
    private Ingredient ingredient;

    @Column(name = "quantita")
    private Double quantity;

    @Column(name = "unita_di_misura")
    @Enumerated(EnumType.STRING)
    private MeasureUnit measureUnit;
}
