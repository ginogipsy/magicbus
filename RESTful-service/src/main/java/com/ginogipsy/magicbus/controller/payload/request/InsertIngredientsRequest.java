package com.ginogipsy.magicbus.controller.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class InsertIngredientsRequest {

    @NotBlank(message = "name of element is necessary!")
    private String nameElement;
    @NotBlank(message = "List of ingredients has to be present!")
    private List<String> ingredientList;
}
