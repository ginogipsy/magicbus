package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Supplier;
import com.ginogipsy.magicbus.dto.SupplierDTO;
import com.ginogipsy.magicbus.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
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

    public SupplierDTO convertToDTO(final Supplier supplier) {
        return Optional.ofNullable(supplier)
                .map(s -> modelMapper.map(s, SupplierDTO.class))
                .orElse(null);
    }

    public SupplierDTO findByName(final String name) {
        log.info("Searching allergen where the name is " + name + "..");
        return Optional.ofNullable(name)
                .map(supplierRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<SupplierDTO> findAll() {
        return supplierRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public SupplierDTO save(final SupplierDTO supplierDTO) {
        log.info("Saving supplier on db..");
        return Optional.ofNullable(supplierDTO)
                .map(this::convertToEntity)
                .map(supplierRepository::save)
                .map(this::convertToDTO)
                .orElse(null);
    }
}
