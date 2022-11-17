package com.ginogipsy.magicbus.exceptionhandler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static com.ginogipsy.magicbus.exceptionhandler.AttributeForErrorEnum.ATTRIBUTE_1;

/**
 * @author ginogipsy
 */
@AllArgsConstructor
@Getter
@ToString
public enum BeErrorCodeEnum {

    GENERIC_ERROR                                ("BE9999", "Generic Error", HttpStatus.INTERNAL_SERVER_ERROR),
    PHONE_NUMBER_IS_PRESENT                      ("BE0001", "Phone number is already present!", HttpStatus.CONFLICT),
    USERNAME_OR_EMAIL_ARE_PRESENT                ("BE0002", "Username or Email are already present!", HttpStatus.CONFLICT),
    CAP_NOT_CORRECT                              ("BE0003","cap is not correct", HttpStatus.BAD_REQUEST),
    FISCAL_CODE_NOT_CORRECT                      ("BE0004","Fiscal code not correct!", HttpStatus.BAD_REQUEST),
    EMAIL_IS_PRESENT                             ("BE0005","Email is already present!", HttpStatus.CONFLICT),
    USERNAME_IS_PRESENT                          ("BE0006","Username is already present!", HttpStatus.CONFLICT),
    FISCAL_CODE_IS_PRESENT                       ("BE0007","Fiscal code is already present!", HttpStatus.CONFLICT),
    BRAND_NOT_FOUND                              ("BE0008","Brand not found!", HttpStatus.NOT_FOUND),
    BRAND_IS_NULL                                ("BE0009","Brand is null!", HttpStatus.NOT_FOUND),
    SUPPLIER_IS_ALREADY_PRESENT                  ("BE0010","Supplier is already present!", HttpStatus.CONFLICT),
    BRAND_IS_ALREADY_PRESENT                     ("BE0011","Brand is already present!", HttpStatus.CONFLICT),
    INGREDIENT_IS_ALREADY_PRESENT                ("BE0012","Ingredient is already present!", HttpStatus.CONFLICT),
    INGREDIENT_IS_NULL                           ("BE0013","Ingredient is null!", HttpStatus.NOT_FOUND),
    INGREDIENT_NOT_FOUND                         ("BE0014","Ingredient not found!", HttpStatus.NOT_FOUND),
    DOUGH_IS_ALREADY_PRESENT                     ("BE0015","Dough is already present!", HttpStatus.CONFLICT),
    DOUGH_IS_NULL                                ("BE0016","Dough is null!", HttpStatus.NOT_FOUND),
    DOUGH_NOT_FOUND                              ("BE0017","Dough not found!", HttpStatus.NOT_FOUND),
    INGREDIENT_DOUGH_IS_ALREADY_PRESENT          ("BE0018","Ingredient is already present for this dough!", HttpStatus.CONFLICT),
    TASTE_IS_ALREADY_PRESENT                     ("BE0019","Taste is already present!", HttpStatus.CONFLICT),
    TASTE_IS_NULL                                ("BE0020","Taste is null!", HttpStatus.NOT_FOUND),
    TASTE_NOT_FOUND                              ("BE0021","Taste not found!", HttpStatus.NOT_FOUND),
    ALLERGEN_IS_ALREADY_PRESENT                  ("BE0022","Allergen is already present!", HttpStatus.CONFLICT),
    ALLERGEN_IS_NULL                             ("BE0023","Allergen is null!", HttpStatus.NOT_FOUND),
    ALLERGEN_NOT_FOUND                           ("BE0024","Allergen not found!", HttpStatus.NOT_FOUND),
    TOPPING_IS_ALREADY_PRESENT                   ("BE0025","Topping is already present!", HttpStatus.CONFLICT),
    TOPPING_IS_NULL                              ("BE0026","Topping is null!", HttpStatus.NOT_FOUND),
    TOPPING_NOT_FOUND                            ("BE0027","Topping not found!", HttpStatus.NOT_FOUND),
    INGREDIENT_TOPPING_IS_ALREADY_PRESENT        ("BE0028","Ingredient is already present for this topping!", HttpStatus.CONFLICT),
    FRIED_IS_ALREADY_PRESENT                     ("BE0029","Fried is already present!", HttpStatus.CONFLICT),
    FRIED_IS_NULL                                ("BE0030","Fried is null!", HttpStatus.NOT_FOUND),
    FRIED_NOT_FOUND                              ("BE0031","Fried not found!", HttpStatus.NOT_FOUND),
    INGREDIENT_FRIED_IS_ALREADY_PRESENT          ("BE0032","Ingredient is already present for this fried!", HttpStatus.CONFLICT),
    REFRESH_TOKEN_NOT_FOUND                      ("BE0033","Refresh token not found in db!", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND                               ("BE0034","Error: Role ${"+ATTRIBUTE_1.getDescription()+"} is not found.", HttpStatus.NOT_FOUND),
    DATA_NOT_CORRECT                             ("BE0035","Inserted data not correct!", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD_FILE_NOT_FOUND              ("BE0036","File with invalid password list not found!", HttpStatus.NOT_FOUND),
    REFRESH_TOKEN_EXPIRED                        ("BE0037","Refresh token was expired. Please make a new signIn request!", HttpStatus.CONFLICT),
    PASSWORD_MISMATCH                            ("BE0038","Password mismatch!", HttpStatus.CONFLICT),
    USER_NOT_FOUND                               ("BE0039","User not found!", HttpStatus.NOT_FOUND),
    ADDRESS_NOT_CORRECT                          ("BE0040","Address not correct!", HttpStatus.BAD_REQUEST),

    ;


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    public static BeErrorCodeEnum fromCode(final String code) {
        return Stream.of(BeErrorCodeEnum.values())
                .filter(ec -> ec.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unexpected value '"+code+"'"));
    }
}
