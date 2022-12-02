package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
/**
 * @author ginogipsy
 */
@Entity
@Table(name = "impasto")
@Data
@EqualsAndHashCode(exclude = {"ingredienti"})
public class Dough {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "impasto_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String name;

    @Column(name = "descrizione")
    private String description;

    @Column(name = "costo_aggiuntivo")
    private Double additionalCost;

    @OneToMany(mappedBy = "dough", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DoughIngredient> ingredients;

}
