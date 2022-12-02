package com.ginogipsy.magicbus.domain.enums;

import java.util.Arrays;
/**
 * @author ginogipsy
 */
public enum Profile {

    GUEST          ("GUEST"),
    USER           ("USER"),
    EDITOR         ("EDITOR"),
    ADMIN          ("ADMIN"),
    MEZZ           ("MEZZ");

    private final String description;

    Profile(final String description){
        this.description = description;
    }

    public String getProfile() {
        return description;
    }

    public static Profile getProfile(final String description){
        return Arrays.stream(Profile.values()).filter(v -> v.getProfile().equals(description)).findAny().orElse(GUEST);
    }
}
