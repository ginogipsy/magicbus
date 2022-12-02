package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.MeasureUnitEnum;
import com.ginogipsy.magicbus.domain.enums.StatusEnum;
import com.ginogipsy.magicbus.domain.enums.DrinkTypeEnum;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "bibita")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bibita_id")
    private Integer id;

    @Column(name = "nome", unique = true)
    private String name;

    @Column(name = "tipologia")
    @Enumerated(EnumType.STRING)
    private DrinkTypeEnum drinkTypeEnum;

    @Column(name = "descrizione")
    private String description;

    @Column(name = "costo_al_litro")
    private Double literCost;

    @Column(name = "formato")
    private Double size;

    @Column(name = "unita_di_misura")
    @Enumerated(EnumType.STRING)
    private MeasureUnitEnum measureUnitEnum;

    @Column(name = "costo")
    private Double cost;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Drink drink = (Drink) o;
        return id != null && Objects.equals(id, drink.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
