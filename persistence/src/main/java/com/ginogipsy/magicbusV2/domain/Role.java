package com.ginogipsy.magicbusV2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"users"})
@Entity(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "profilo", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private Profilo profilo;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
