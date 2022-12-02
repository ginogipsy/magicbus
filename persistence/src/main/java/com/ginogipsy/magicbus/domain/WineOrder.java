package com.ginogipsy.magicbus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
/**
 * @author ginogipsy
 */
@Data
@EqualsAndHashCode(exclude = {"order", "wine"})
@Entity(name = "ordine_vino")
public class WineOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordinevino_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordine_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vino_id")
    private Wine wine;

    @Column(name = "quantita")
    private Integer quantity;

    @Column(name = "annullato", columnDefinition = "TINYINT", length = 1)
    private Boolean canceled;

    @Column(name = "costo_totale")
    private Double totalCost;

    public void setCostoTotale(){
        this.totalCost = this.quantity * this.wine.getCost();
    }
}
