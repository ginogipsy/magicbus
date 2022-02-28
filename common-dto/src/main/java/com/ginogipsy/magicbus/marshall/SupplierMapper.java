package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Supplier;
import com.ginogipsy.magicbus.dto.SupplierDTO;
import com.ginogipsy.magicbus.repository.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SupplierMapper {

    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;


    public SupplierMapper(ModelMapper modelMapper, SupplierRepository supplierRepository) {
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
    }

    public Supplier convertToEntity(final SupplierDTO supplierDTO){
        return Optional.ofNullable(supplierDTO)
                .map(s -> modelMapper.map(s, Supplier.class))
            .orElse(null);
    }

    public SupplierDTO convertToDTO(final Supplier supplier){
        return Optional.ofNullable(supplier)
                .map(s -> modelMapper.map(s, SupplierDTO.class))
            .orElse(null);
    }
}
