package com.ginogipsy.magicbus.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private int id;

    @Email(message = "It is necessary an email format!")
    private String email;

    @Min(value = 4, message = "Username must be at least 4 characters long!")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String name;
    private String surname;

    @Size(min = 9, max = 11, message = "Cell number must have 9 to 11 digits!")
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
