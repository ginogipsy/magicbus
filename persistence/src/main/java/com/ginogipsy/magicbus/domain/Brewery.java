package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
/**
 * @author ginogipsy
 */
@Entity
@Table(name = "birrificio")
@Data
@EqualsAndHashCode(exclude = "birrificio")
public class Brewery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birrificio_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String name;

    @Column(name = "descrizione")
    private String description;

    @Column(name = "ubicazione")
    private String location;

    @Column(name = "nazione")
    private String nationality;

    @OneToMany(mappedBy = "brewery", fetch = FetchType.LAZY)
    private Set<Beer> beers;
}
