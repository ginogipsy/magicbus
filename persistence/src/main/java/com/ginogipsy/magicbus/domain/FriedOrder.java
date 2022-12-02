package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
/**
 * @author ginogipsy
 */
@Entity
@Table(name = "ordine_fritto")
@Data
@EqualsAndHashCode(exclude = {"ordine", "fritto"})
public class FriedOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordinefritto_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordine_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fritto_id")
    private Fried fried;

    @Column(name = "quantita")
    private Integer quantity;

    @Column(name = "annullato", columnDefinition = "TINYINT", length = 1)
    private Boolean canceled;

    @Column(name = "costo_totale")
    private Double totalCost;

    public void setCostoTotale(){
        this.totalCost = this.quantity * this.fried.getCost();
    }
}

