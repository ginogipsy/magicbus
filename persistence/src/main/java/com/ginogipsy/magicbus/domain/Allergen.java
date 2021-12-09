package com.ginogipsy.magicbus.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Entity(name = "allergene")
public class Allergen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergene_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String name;

    @Column(name = "descrizione")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ingrediente_allergene",
            joinColumns = @JoinColumn(name = "ingrediente_id"),
            inverseJoinColumns = @JoinColumn(name = "allergene_id"))
    private Set<Ingrediente> ingrediants;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Allergen allergen = (Allergen) o;
        return id != null && Objects.equals(id, allergen.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

