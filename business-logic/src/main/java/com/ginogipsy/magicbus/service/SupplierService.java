package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.SupplierDTO;

import java.util.List;

/**
 * @author ginogipsy
 */

public interface SupplierService {

    SupplierDTO findByName(final String name);

    List<SupplierDTO> findAll();

    SupplierDTO insert(final SupplierDTO supplierDTO);
}
