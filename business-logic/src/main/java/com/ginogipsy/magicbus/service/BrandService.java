package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.BrandDTO;

import java.util.List;

public interface BrandService {

    BrandDTO findByName(final String name);

    List<BrandDTO> findAll();

    BrandDTO save(final BrandDTO brandDTO);
}
