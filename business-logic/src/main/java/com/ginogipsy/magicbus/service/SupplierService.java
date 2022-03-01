package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {

    SupplierDTO findByName(final String name);

    List<SupplierDTO> findAll();

    SupplierDTO save(final SupplierDTO supplierDTO);
}
