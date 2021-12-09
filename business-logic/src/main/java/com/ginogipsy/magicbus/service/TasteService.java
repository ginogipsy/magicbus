package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.TasteDTO;
import com.ginogipsy.magicbus.dto.UserDTO;

import java.util.List;

public interface TasteService {

    List<TasteDTO> findByStatus(String status);
    TasteDTO findByName(String name);
    List<TasteDTO> findTasteWhereNamesContains(String name);
    List<TasteDTO> findByBase(String base);
    List<TasteDTO> findByProductCategory(String productCategory);
    List<TasteDTO> findByAvailabilityPeriod(String availabilityPeriod);
    List<TasteDTO> findByAvailabilityAndStatus(boolean availability, String status);
    List<TasteDTO> findByAvailableAndAvailabilityPeriod(boolean availability, String availabilityPeriod);
    List<TasteDTO> findByUserEntered(boolean userEntered);
    List<TasteDTO> findByUserEnteredAndStatus(boolean userEntered, String status);
    TasteDTO insertTaste(TasteDTO tasteDTO, UserDTO userDTO);
}
