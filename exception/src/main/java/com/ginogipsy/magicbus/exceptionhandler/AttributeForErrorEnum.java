package com.ginogipsy.magicbus.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.stream.Stream;

/**
 * @author ginogipsy
 */

@AllArgsConstructor
@Getter
public enum AttributeForErrorEnum {

    ATTRIBUTE_1(1, "attribute1"),
    ATTRIBUTE_2(2, "attribute2"),
    ATTRIBUTE_3(3, "attribute3"),
    ATTRIBUTE_4(4, "attribute4"),
    ATTRIBUTE_5(5, "attribute5"),
    ATTRIBUTE_6(6, "attribute6"),
    ATTRIBUTE_7(7, "attribute7"),

    ;

    private final int id;
    private final String description;

    public static AttributeForErrorEnum get(final int id) {
        return Stream.of(AttributeForErrorEnum.values())
                .filter(afe -> afe.getId() == id)
                .findFirst()
                .orElse(ATTRIBUTE_1);
    }

    public static String getDescription(final int id) {
        return Stream.of(AttributeForErrorEnum.values())
                .filter(afe -> afe.getId() == id)
                .findFirst()
                .map(AttributeForErrorEnum::getDescription)
                .orElse(ATTRIBUTE_1.getDescription());
    }

    @Override
    public String toString() {
        return description;
    }
}
