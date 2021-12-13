package com.ginogipsy.magicbus.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ginogipsy.magicbus.domain.enums.*;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class ToppingDTO {

    @NotNull
    @NonNull
    @NotEmpty
    private String name;

    @NotNull
    @NonNull
    @NotEmpty
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
