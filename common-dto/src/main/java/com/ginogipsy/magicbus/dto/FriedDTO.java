package com.ginogipsy.magicbus.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ginogipsy.magicbus.domain.enums.ProductCategory;
import com.ginogipsy.magicbus.domain.enums.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class FriedDTO {

    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String friedDescription;
    private Double cost;
    private StatusEnum statusEnum;
    private List<String> ingredients;

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
