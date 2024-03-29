package com.ginogipsy.magicbus.domain;

import com.ginogipsy.magicbus.domain.enums.ProductCategoryEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
/**
 * @author ginogipsy
 */
@Entity
@Table(name = "ordine_gusto")
@Data
@EqualsAndHashCode(exclude = {"ordine", "gusto", "impasto"})
public class ToppingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordinegusto_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordine_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gusto_id")
    private Topping topping;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "impasto_id")
    private Dough dough;

    @Column(name = "quantita")
    private Double quantity;

    @Column(name = "annullato", columnDefinition = "TINYINT", length = 1)
    private Boolean canceled;

    @Column(name = "costo_totale")
    private Double totalCost;

    public void setCostoTotale(){
        this.totalCost = this.quantity * this.topping.getCost();
    }

    public void setDough(Dough dough){
        if(topping.getProductCategoryEnum().equals(ProductCategoryEnum.PIZZA) || topping.getProductCategoryEnum().equals(ProductCategoryEnum.CALZONE) || topping.getProductCategoryEnum().equals(ProductCategoryEnum.PANINO)){
            this.dough = dough;
            this.totalCost = totalCost + dough.getAdditionalCost()*this.quantity;
        }
    }
}

