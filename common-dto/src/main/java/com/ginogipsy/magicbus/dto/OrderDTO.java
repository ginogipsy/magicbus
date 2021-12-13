package com.ginogipsy.magicbus.dto;

import lombok.Data;
import org.joda.time.LocalDate;

import java.util.List;

@Data
public class OrderDTO {

    private Integer id;
    private LocalDate insertionTime;
    private LocalDate approvalTime;
    private AvailableTimeDTO availableTime;
    private UserDTO user;
    private List<FriedOrderDTO> friedOrders;
    private List<ToppingOrderDTO> tastesOrders;
    private List<DrinkOrderDTO> drinkOrders;
    private List<BeerOrderDTO> beerOrders;
    private List<WineOrderDTO> wineOrders;
    private PaymentOrderDTO payment;

}
