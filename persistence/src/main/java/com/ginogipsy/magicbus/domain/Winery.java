package com.ginogipsy.magicbus.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
/**
 * @author ginogipsy
 */
@Entity
@Table(name = "cantina")
@Getter
@Setter
public class Winery {

    @Id
    @GeneratedValue
    @Column(name = "cantina_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String name;

    @Column(name = "descrizione")
    private String description;

    @Column(name = "ubicazione")
    private String location;

    @Column(name = "nazione")
    private String nationality;

    @OneToMany(mappedBy = "winery", fetch = FetchType.LAZY)
    private Set<Wine> wines;
}