package com.ginogipsy.magicbus.configuration;

import com.ginogipsy.magicbus.domain.User;
import org.modelmapper.AbstractConverter;


import java.util.Optional;
import java.util.Set;

/**
 * @author ginogipsy
 */

public class AppreciatedUsersListToCountConverter extends AbstractConverter<Set<User>, Integer> {

    @Override
    protected Integer convert(Set<User> users) {
        return Optional.ofNullable(users).map(Set::size).orElse(0);
    }
}
