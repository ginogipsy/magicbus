package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.DoughDTO;

import java.util.List;

/**
 * @author ginogipsy
 */

public interface DoughService {

    DoughDTO findByName(final String name);

    List<DoughDTO> findAll();

    DoughDTO save(final DoughDTO doughDTO);
}
