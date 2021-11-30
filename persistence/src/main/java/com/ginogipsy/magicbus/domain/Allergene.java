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
public class Allergene {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergene_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @ManyToMany
    @JoinTable(name = "ingrediente_allergene",
            joinColumns = @JoinColumn(name = "ingrediente_id"),
            inverseJoinColumns = @JoinColumn(name = "allergene_id"))
    private Set<Ingrediente> ingredienti;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Allergene allergene = (Allergene) o;
        return id != null && Objects.equals(id, allergene.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

