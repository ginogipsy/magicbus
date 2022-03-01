package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.AllergenDTO;

import java.util.List;

public interface AllergenService {

    List<AllergenDTO> findAll();

    AllergenDTO findByName(final String name);

    AllergenDTO save(final AllergenDTO allergenDTO);
}
