package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.dto.SupplierDTO;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.*;

@Slf4j
@Service
public class SupplierServiceImpl implements SupplierService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;

    public SupplierServiceImpl(MapperFactory mapperFactory, StringUtility stringUtility) {
        this.mapperFactory = mapperFactory;
        this.stringUtility = stringUtility;
    }

    @Override
    public SupplierDTO findByName(final String name) {
        return Optional.ofNullable(privateFindByName(name))
                .orElseThrow(() -> new MagicbusException(BRAND_NOT_FOUND, "Brand " + name + " not found!"));
    }

    @Override
    public List<SupplierDTO> findAll() {
        return mapperFactory.getSupplierMapper().findAll();
    }

    @Override
    public SupplierDTO save(final SupplierDTO supplierDTO) {
        log.info("Checking if this supplier is already present..");
        final String name = Optional.ofNullable(supplierDTO)
                .map(SupplierDTO::getName)
                .orElseThrow(() -> new MagicbusException(BRAND_IS_NULL));

        if (Optional.ofNullable(privateFindByName(name)).isPresent()) {
            log.error("It is already present a supplier called " + name + "!");
            throw new MagicbusException(SUPPLIER_IS_ALREADY_PRESENT, "It is already present a supplier called " + name + "!");
        }

        log.info("Formatting all..");
        privateFormatIngredient(supplierDTO);
        log.info("Saving the supplier..");
        return mapperFactory.getSupplierMapper().save(supplierDTO);
    }

    private SupplierDTO privateFindByName(final String name) {
        return Optional.ofNullable(name)
                .map(n -> mapperFactory.getSupplierMapper().findByName(n))
                .orElse(null);
    }

    private void privateFormatIngredient(final SupplierDTO supplierDTO) {
        Optional.ofNullable(supplierDTO.getName())
                .ifPresent(n -> supplierDTO.setName(stringUtility.formatAllMinusc(n)));
        Optional.ofNullable(supplierDTO.getStreet())
                .ifPresent(n -> supplierDTO.setStreet(stringUtility.formatAllMinusc(n)));
        Optional.ofNullable(supplierDTO.getPostalCode())
                .filter(stringUtility::checkPostalCode)
                .ifPresent(supplierDTO::setPostalCode);
        Optional.ofNullable(supplierDTO.getCity())
                .ifPresent(n -> supplierDTO.setCity(stringUtility.formatAllMinusc(n)));
    }
}
