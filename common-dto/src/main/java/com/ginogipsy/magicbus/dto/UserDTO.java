package com.ginogipsy.magicbus.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ginogipsy.magicbus.domain.Taste;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {

    private int id;

    @NonNull
    private String email;
    @NonNull
    private String username;

    @NonNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String name;
    private String surname;

    @NonNull
    private String cellNumber;
    private String street;
    private String houseNumber;
    private String city;
    private String postalCode;
    private String fiscalCode;

    //@JsonIgnore
    private Boolean isEnabled;

    @JsonIgnore
    private Set<RoleDTO> roles;
    private Set<TasteDTO> favoriteTastes;
    private Set<FriedDTO> favoriteFried;
    private Set<BeerDTO> favoriteBeers;
    private Set<WineDTO> favoriteWines;
    private Set<OrderDTO> orders;
    private Set<Taste> tastesInserted;



}
