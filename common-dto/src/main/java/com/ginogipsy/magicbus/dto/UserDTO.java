package com.ginogipsy.magicbus.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {

    private int id;

    private String email;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String name;
    private String surname;
    private String cellNumber;
    private String address;
    private String houseNumber;
    private String city;
    private String postalCode;
    private String fiscalCode;
    private Boolean isEnabled;

    @JsonIgnore
    private Set<RoleDTO> roles;
    private Set<ToppingDTO> favoriteTastes;
    private Set<FriedDTO> favoriteFried;
    private Set<BeerDTO> favoriteBeers;
    private Set<WineDTO> favoriteWines;
    private Set<OrderDTO> orders;
    private List<String> toppingsInserted;

}
