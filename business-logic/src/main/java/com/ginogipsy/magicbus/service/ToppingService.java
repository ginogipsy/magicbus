package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.dto.UserDTO;

import java.util.List;

/**
 * @author ginogipsy
 */

public interface ToppingService {

    List<ToppingDTO> findByStatus(final String status);

    ToppingDTO findByName(final String name);

    List<ToppingDTO> findTasteWhereNamesContains(final String name);

    List<ToppingDTO> findByBase(final String base);

    List<ToppingDTO> findByProductCategory(final String productCategory);

    List<ToppingDTO> findByAvailabilityPeriod(final String availabilityPeriod);

    List<ToppingDTO> findByAvailabilityAndStatus(final boolean availability, final String status);

    List<ToppingDTO> findByAvailableAndAvailabilityPeriod(final boolean availability, final String availabilityPeriod);

    List<ToppingDTO> findByUserEntered(final boolean userEntered);
    List<ToppingDTO> findByUserEnteredAndStatus(final boolean userEntered, final String status);

    ToppingDTO insert(final ToppingDTO toppingDTO, final UserDTO userDTO);
}
