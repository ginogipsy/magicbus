package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Supplier;
import com.ginogipsy.magicbus.dto.SupplierDTO;
import com.ginogipsy.magicbus.repository.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;


    public SupplierMapper(ModelMapper modelMapper, SupplierRepository supplierRepository) {
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
    }

    public Supplier convertToEntity(SupplierDTO supplierDTO){
        return (supplierDTO != null) ? modelMapper.map(supplierDTO, Supplier.class) : null;
    }

    public SupplierDTO convertToDTO(Supplier supplier){
        return (supplier != null) ? modelMapper.map(supplier, SupplierDTO.class) : null;
    }
}
