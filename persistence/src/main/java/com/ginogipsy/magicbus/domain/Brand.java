package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"fornitore", "descrizione"})
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