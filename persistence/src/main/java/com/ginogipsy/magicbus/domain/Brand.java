package com.ginogipsy.magicbus.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
/**
 * @author ginogipsy
 */
@Entity
@Getter
@Setter
//@EqualsAndHashCode(exclude = {"fornitore", "descrizione"})
@Table(name = "marca_prodotto")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marcaprodotto_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String name;

    @Column(name = "descrizione")
    private String description;

    @ManyToMany(mappedBy = "brands", fetch = FetchType.LAZY)
    private Set<Supplier> suppliers;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private Set<Ingredient> ingredients;

}
