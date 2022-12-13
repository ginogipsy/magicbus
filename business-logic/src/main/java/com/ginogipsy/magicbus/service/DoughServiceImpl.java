package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.dto.DoughDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class DoughServiceImpl implements DoughService {

    private final StringUtility stringUtility;
    private final MapperFactory mapperFactory;

    @Override
    public DoughDTO findByName(final String name) {
        return privateFindByName(name)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.INGREDIENT_NOT_FOUND, "Ingredient " + name + " not found!"));
    }

    @Override
    public List<DoughDTO> findAll() {
        return mapperFactory.getDoughMapper().findAll();
    }

    @Override
    public DoughDTO save(final DoughDTO doughDTO) {
        final String name = Optional.ofNullable(doughDTO)
                .map(DoughDTO::getName)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.INGREDIENT_IS_NULL, "Ingredient doesn't have to be null!"));

        log.info("DoughServiceImpl - save() -> Checking if this dough is already present..");
        if (privateFindByName(name).isPresent()) {
            final String resultString = "It is already present a dough called " + name + "!";
            log.error("DoughServiceImpl - save() -> {}", resultString);
            throw new MagicbusException(BeErrorCodeEnum.DOUGH_IS_ALREADY_PRESENT, resultString);
        }

        privateFormatDough(doughDTO);
        log.info("DoughServiceImpl - save() -> Saving the dough..");
        return mapperFactory.getDoughMapper().save(doughDTO)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.SAVE_FAILED));
    }

    private Optional<DoughDTO> privateFindByName(final String doughName) {
        stringUtility.formatAllLower(doughName);
        log.info("DoughServiceImpl - privateFindByName() -> Finding dough named '{}'..", doughName);

        return Optional.ofNullable(doughName)
                .flatMap(n -> mapperFactory.getDoughMapper().findByName(n));
    }

    private void privateFormatDough(final DoughDTO doughDTO) {
        log.info("DoughServiceImpl - privateFormatDough() -> Formatting name and description..");

        if(doughDTO == null) {
            log.warn("DoughServiceImpl - privateFormatDough() -> doughDTO is null!");
            return;
        }

        Optional.ofNullable(doughDTO.getName())
                .ifPresent(n -> doughDTO.setName(stringUtility.formatAllLower(n)));
        Optional.ofNullable(doughDTO.getDescription())
                .ifPresent(n -> doughDTO.setDescription(stringUtility.formatAllLower(n)));
    }
}
