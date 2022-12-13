package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Supplier;
import com.ginogipsy.magicbus.dto.SupplierDTO;
import com.ginogipsy.magicbus.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class SupplierMapper {

    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;

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

    public Optional<Supplier> convertToEntity(final Optional<SupplierDTO> supplierDTO) {
        return Optional.ofNullable(supplierDTO)
                .map(s -> modelMapper.map(s, Supplier.class));
    }

    public Optional<SupplierDTO> convertToDTO(final Optional<Supplier> supplier) {
        return Optional.ofNullable(supplier)
                .map(s -> modelMapper.map(s, SupplierDTO.class));
    }

    public Optional<SupplierDTO> findByName(final String name) {
        log.info("SupplierMapper - findByName() -> Searching supplier where name is '{}' ..", name);
        return Optional.ofNullable(name)
                .flatMap(supplierRepository::findByName)
                .map(this::convertToDTO);
    }

    public List<SupplierDTO> findAll() {
        return supplierRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Optional<SupplierDTO> save(final SupplierDTO supplierDTO) {
        log.info("SupplierMapper - save() -> Saving supplier on db..");
        return Optional.ofNullable(supplierDTO)
                .map(this::convertToEntity)
                .map(supplierRepository::save)
                .map(this::convertToDTO);
    }
}
