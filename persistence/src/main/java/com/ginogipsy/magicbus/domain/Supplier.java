package com.ginogipsy.magicbus.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
/**
 * @author ginogipsy
 */
@Entity
@Table(name = "fornitore")
@Getter
@Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fornitore_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String name;

    @Column(name = "via")
    private String street;

    @Column(name = "civico")
    private Integer number;

    @Column(name = "cap")
    private String postalCode;

    @Column(name = "citta")
    private String city;

    @Column(name = "partita_iva")
    private String vatNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "marca_fornitore",
            joinColumns = @JoinColumn(name = "marca_id"),
            inverseJoinColumns = @JoinColumn(name = "fornitore_id"))
    private Set<Brand> brands;
}
