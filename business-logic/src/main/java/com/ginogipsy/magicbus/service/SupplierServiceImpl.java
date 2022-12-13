package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.dto.SupplierDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.*;

/**
 * @author ginogipsy
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;

    @Override
    public SupplierDTO findByName(final String name) {
        return privateFindByName(name)
                .orElseThrow(() -> new MagicbusException(BRAND_NOT_FOUND, "Brand " + name + " not found!"));
    }

    @Override
    public List<SupplierDTO> findAll() {
        return mapperFactory.getSupplierMapper().findAll();
    }

    @Override
    public SupplierDTO insert(final SupplierDTO supplierDTO) {
        log.info("SupplierServiceImpl - insert() -> Checking if supplier is null..");
        final String supplierName = Optional.ofNullable(supplierDTO)
                .map(SupplierDTO::getName)
                .orElseThrow(() -> new MagicbusException(SUPPLIER_IS_NULL));

        if (privateFindByName(supplierName).isPresent()) {
            log.error("SupplierServiceImpl - insert() -> It is already present a supplier called {}!", supplierName);
            throw new MagicbusException(SUPPLIER_IS_ALREADY_PRESENT, "It is already present a supplier called " + supplierName + "!");
        }

        privateFormatSupplier(supplierDTO);
        log.info("SupplierServiceImpl - insert() -> Saving the supplier..");
        return mapperFactory.getSupplierMapper().save(supplierDTO)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.SAVE_FAILED));
    }

    private Optional<SupplierDTO> privateFindByName(final String name) {
        return Optional.ofNullable(name)
                .flatMap(n -> mapperFactory.getSupplierMapper().findByName(n));
    }

    private void privateFormatSupplier(final SupplierDTO supplierDTO) {
        log.info("SupplierServiceImpl - privateFormatSupplier() -> Formatting name and description..");

        if(supplierDTO == null) {
            log.warn("SupplierServiceImpl - privateFormatSupplier() -> supplierDTO is null!");
            return;
        }

        Optional.ofNullable(supplierDTO.getName())
                .ifPresent(n -> supplierDTO.setName(stringUtility.formatAllLower(n)));
        Optional.ofNullable(supplierDTO.getStreet())
                .ifPresent(n -> supplierDTO.setStreet(stringUtility.formatAllLower(n)));
        Optional.ofNullable(supplierDTO.getPostalCode())
                .filter(stringUtility::checkPostalCode)
                .ifPresent(supplierDTO::setPostalCode);
        Optional.ofNullable(supplierDTO.getCity())
                .ifPresent(n -> supplierDTO.setCity(stringUtility.formatAllLower(n)));
    }
}
