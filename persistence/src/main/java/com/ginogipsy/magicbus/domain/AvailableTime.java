package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
/**
 * @author ginogipsy
 */
@Entity
@Data
@Table(name = "orario_disponibile")
@EqualsAndHashCode(exclude = {"id","ordini"})
public class AvailableTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orariodisponibile_id")
    private Integer id;

    @Column(name = "ora", nullable = false)
    private Integer hour;

    @Column(name = "minuti", nullable = false)
    private Integer minutes;

    @OneToMany(mappedBy = "availableTime", fetch = FetchType.LAZY)
    private Set<Order> orders;
}
