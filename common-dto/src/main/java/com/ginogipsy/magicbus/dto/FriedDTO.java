package com.ginogipsy.magicbus.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ginogipsy.magicbus.domain.enums.ProductCategory;
import com.ginogipsy.magicbus.domain.enums.Status;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class FriedDTO {

    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String friedDescription;
    private Double cost;
    private Status status;
    private Set<FriedIngredientDTO> ingredients;

    @JsonIgnore
    private Byte[] image;
    private ProductCategory productCategory;
    private Boolean available;
    private boolean userEntered;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserDTO userCreator;
    private String username;
    private int appreciations;
}
