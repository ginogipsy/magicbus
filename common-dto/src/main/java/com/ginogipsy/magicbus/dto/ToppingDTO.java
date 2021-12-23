package com.ginogipsy.magicbus.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ginogipsy.magicbus.domain.enums.*;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class ToppingDTO {


    @NonNull
    @NotBlank(message = "Name is necessary!")
    @Min(value = 2, message = "A name has at least 2 characters!")
    private String name;

    @NonNull
    @NotBlank
    @Min(value = 10, message = "A name has at least 10 chars!")
    private String toppingDescription;
    private Boolean traditional;
    private Status status;
    private Double cost;
    private Boolean veganOption;

    @JsonIgnore
    private Byte[] image;
    private Base base;
    private AvailabilityPeriod availabilityPeriod;
    private ProductCategory productCategory;

    @JsonIgnore
    private Set<UserDTO> users;
    private boolean available;
    private boolean userEntered;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserDTO userCreator;
    private String username;
    private Integer appreciations;









}
