package com.ginogipsy.magicbus.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "birra")
public class Birra{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birra_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String nome;

    //@Lob
    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "grado_alcolico")
    private Double gradoAlcolico;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "tipologia_birra")
    @Enumerated(EnumType.STRING)
    private TipologiaBirra tipologiaBirra;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "birrificio_id")
    private Birrificio birrificio;

    @ManyToMany(mappedBy = "birrePreferite")
    @ToString.Exclude
    private Set<User> users;

    @Column(name = "disponibile", columnDefinition = "TINYINT", length = 1)
    private Boolean disponibile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Birra birra = (Birra) o;
        return id != null && Objects.equals(id, birra.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
