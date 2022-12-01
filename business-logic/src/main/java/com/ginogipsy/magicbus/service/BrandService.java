package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.BrandDTO;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    Optional<BrandDTO> findByName(final String name);

    List<BrandDTO> findAll();

    BrandDTO insert(final BrandDTO brandDTO);
}
