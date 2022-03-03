package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.dto.UserDTO;

import java.util.List;

public interface ToppingService {

    List<ToppingDTO> findByStatus(String status);

    ToppingDTO findByName(String name);

    List<ToppingDTO> findTasteWhereNamesContains(String name);

    List<ToppingDTO> findByBase(String base);

    List<ToppingDTO> findByProductCategory(String productCategory);

    List<ToppingDTO> findByAvailabilityPeriod(String availabilityPeriod);

    List<ToppingDTO> findByAvailabilityAndStatus(boolean availability, String status);

    List<ToppingDTO> findByAvailableAndAvailabilityPeriod(boolean availability, String availabilityPeriod);

    List<ToppingDTO> findByUserEntered(boolean userEntered);
    List<ToppingDTO> findByUserEnteredAndStatus(boolean userEntered, String status);

    ToppingDTO insertTopping(ToppingDTO toppingDTO, UserDTO userDTO);
}
