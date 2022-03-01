package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.AvailabilityPeriod;
import com.ginogipsy.magicbus.domain.enums.IngredientType;
import com.ginogipsy.magicbus.domain.enums.MeasureUnit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingrediente")
@Data
@EqualsAndHashCode(exclude = {"tipoAllergene", "marca", "gusti", "fritti", "impasti"})
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingrediente_id")
    private Integer id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descrizione")
    private String description;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean available;

    @Column(name = "costo_aggiunta_cliente")
    private Double additionalCostForClient;

    @Column(name = "costo_acquisto")
    private Double purchaseCost;

    @Column(name = "unita_di_misura")
    @Enumerated(EnumType.STRING)
    private MeasureUnit measureUnit;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    private Set<Allergen> allergens;

    @Column(name = "tipologia_ingrediente")
    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    @Column(name = "periodo_disponibilita")
    @Enumerated(EnumType.STRING)
    private AvailabilityPeriod availabilityPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id")
    private Brand brand;

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    private Set<ToppingIngredient> toppings;

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    private Set<FriedIngredient> fried;

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    private Set<DoughIngredient> doughs;


}
