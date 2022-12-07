package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.BrandDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

public interface BrandService {

    BrandDTO findByName(final String name);

    List<BrandDTO> findAll();

    BrandDTO insert(final BrandDTO brandDTO);
}
