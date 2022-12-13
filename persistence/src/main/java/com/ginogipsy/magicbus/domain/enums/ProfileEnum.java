package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum ProfileEnum {

    GUEST          ("GUEST"),
    USER           ("USER"),
    EDITOR         ("EDITOR"),
    ADMIN          ("ADMIN"),
    MEZZ           ("MEZZ");

    private final String description;

    ProfileEnum(final String description){
        this.description = description;
    }

    public String getProfile() {
        return description;
    }

    public static ProfileEnum getProfile(final String description){
        return Arrays.stream(ProfileEnum.values())
                .filter(v -> v.getProfile().equals(description))
                .findAny()
                .orElse(GUEST);
    }
}
