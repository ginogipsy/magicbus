package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.BeerType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "birra")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birra_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String name;

    //@Lob
    @Column(name = "descrizione")
    private String description;

    @Column(name = "grado_alcolico")
    private Double alcoholContent;

    @Column(name = "costo")
    private Double cost;

    @Column(name = "tipologia_birra")
    @Enumerated(EnumType.STRING)
    private BeerType beerType;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "birrificio_id")
    private Brewery brewery;

    @ManyToMany(mappedBy = "birrePreferite", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<User> users;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean available;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Beer beer = (Beer) o;
        return id != null && Objects.equals(id, beer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
