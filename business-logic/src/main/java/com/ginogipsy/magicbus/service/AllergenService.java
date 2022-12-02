package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.AllergenDTO;

import java.util.List;

/**
 * @author ginogipsy
 */

public interface AllergenService {

    List<AllergenDTO> findAll();

    AllergenDTO findByName(final String name);

    AllergenDTO insert(final AllergenDTO allergenDTO);
}
