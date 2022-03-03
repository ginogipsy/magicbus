package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.UserUtility;
import com.ginogipsy.magicbus.customexception.gusto.GustoIsPresentException;
import com.ginogipsy.magicbus.customexception.notfound.BaseNotFoundException;
import com.ginogipsy.magicbus.customexception.notfound.GustoNotFoundException;
import com.ginogipsy.magicbus.customexception.notfound.StatusProductsNotFoundException;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ToppingServiceImpl implements ToppingService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;
    private final UserUtility userUtility;

    public ToppingServiceImpl(MapperFactory mapperFactory, StringUtility stringUtility, UserUtility userUtility) {
        this.mapperFactory = mapperFactory;
        this.stringUtility = stringUtility;
        this.userUtility = userUtility;
    }

    @Override
    public List<ToppingDTO> findByStatus(final String status) throws StatusProductsNotFoundException {
           return mapperFactory.getToppingMapper().findByStatus(status);
    }

    @Override
    public ToppingDTO findByName(final String tasteName) throws GustoNotFoundException {
        log.info("Check if taste name is null..");
        final String n = Optional.ofNullable(stringUtility.formatAllMinusc(tasteName))
                .orElseThrow(() -> new GustoNotFoundException("test name is null!"));
        log.info("Search taste name..");
        return Optional.ofNullable(mapperFactory.getToppingMapper()
                .findByName(n)).orElseThrow(() -> new GustoNotFoundException("Taste "+tasteName+" not found!"));
    }

    @Override
    public List<ToppingDTO> findTasteWhereNamesContains(final String tasteName) throws GustoNotFoundException {
        log.info("Check if taste name is null..");
        final String n = Optional.ofNullable(stringUtility.formatAllMinusc(tasteName))
                .orElseThrow(() -> new GustoNotFoundException("taste name is null!"));
        log.info("Search taste name..");
        return mapperFactory.getToppingMapper().findByNomeContains(n);
    }

    @Override
    public List<ToppingDTO> findByBase(final String base) throws BaseNotFoundException {
        return mapperFactory.getToppingMapper().findByBase(base);
    }

    @Override
    public List<ToppingDTO> findByProductCategory(String productCategory) {
        return mapperFactory.getToppingMapper().findByProductCategory(productCategory);
    }

    @Override
    public List<ToppingDTO> findByAvailabilityPeriod(String availabilityPeriod) {
        return mapperFactory.getToppingMapper().findByAvailabilityPeriod(availabilityPeriod);
    }

    @Override
    public List<ToppingDTO> findByAvailabilityAndStatus(boolean available, String status) {
        return mapperFactory.getToppingMapper().findByAvailableAndStatus(available, status);
    }

    @Override
    public List<ToppingDTO> findByAvailableAndAvailabilityPeriod(boolean available, String availabilityPeriod) {
        return mapperFactory.getToppingMapper().findByAvailableAndAvailabilityPeriod(available, availabilityPeriod);
    }

    @Override
    public List<ToppingDTO> findByUserEntered(boolean userEntered) {
        return mapperFactory.getToppingMapper().findByUserEntered(userEntered);
    }

    @Override
    public List<ToppingDTO> findByUserEnteredAndStatus(boolean userEntered, String status) {
        return mapperFactory.getToppingMapper().findByUserEnteredAndStatus(userEntered, status);
    }

    @Override
    public ToppingDTO insertTopping(ToppingDTO toppingDTO, UserDTO userDTO) {
        log.info("Insert taste - START");
        if (userUtility.isOnlyAnUser(userDTO)) {
            toppingDTO.setUserEntered(true);
            toppingDTO.setAvailable(false);
            toppingDTO.setStatus(Status.INSERITO);
            toppingDTO.setName(stringUtility.formatAllMinusc(toppingDTO.getName()).concat(" by ").concat(userDTO.getUsername()));
        } else {
            toppingDTO.setUserEntered(false);
            toppingDTO.setAvailable(true);
            toppingDTO.setStatus(Status.DISPONIBILE);
            toppingDTO.setName(stringUtility.formatAllMinusc(toppingDTO.getName()));
        }

        if(mapperFactory.getToppingMapper().findByName(toppingDTO.getName()) != null){
            log.error("Taste is already present in DB!");
            throw new GustoIsPresentException("Taste ".concat(toppingDTO.getName()).concat(" is already present!"));
        }

        toppingDTO.setToppingDescription(stringUtility.formatAllMinusc(toppingDTO.getToppingDescription()));
        toppingDTO.setUserCreator(userDTO);
        log.info("Insert taste - FINISH");
        return mapperFactory.getToppingMapper().save(toppingDTO);
    }
}
