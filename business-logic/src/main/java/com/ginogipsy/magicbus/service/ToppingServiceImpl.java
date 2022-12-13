package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.UserUtility;
import com.ginogipsy.magicbus.domain.enums.StatusEnum;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.SAVE_FAILED;
import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.TOPPING_IS_NULL;

/**
 * @author ginogipsy
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ToppingServiceImpl implements ToppingService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;
    private final UserUtility userUtility;

    @Override
    public List<ToppingDTO> findByStatus(final String status) throws MagicbusException {
           return mapperFactory.getToppingMapper().findByStatus(status);
    }

    @Override
    public ToppingDTO findByName(final String toppingName) throws MagicbusException {
       return privateFindByName(toppingName)
               .orElseThrow(() -> new MagicbusException(TOPPING_IS_NULL));
    }

    @Override
    public List<ToppingDTO> findTasteWhereNamesContains(final String toppingName) throws MagicbusException {
        log.info("ToppingServiceImpl - findTasteWhereNamesContains() -> Checking if topping is null..");

        final String n = Optional.ofNullable(stringUtility.formatAllLower(toppingName))
                .orElseThrow(() -> new MagicbusException(TOPPING_IS_NULL));

        log.info("ToppingServiceImpl - findTasteWhereNamesContains() -> Search topping that names contain..");
        return mapperFactory.getToppingMapper().findByNomeContains(n);
    }

    @Override
    public List<ToppingDTO> findByBase(final String base) throws MagicbusException {
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
    public ToppingDTO insert(final ToppingDTO toppingDTO, final UserDTO userDTO) {
        log.info("ToppingServiceImpl - insert() -> Checking if topping is null..");
        Optional.ofNullable(toppingDTO).orElseThrow(() -> new MagicbusException(TOPPING_IS_NULL));

        if (userUtility.isOnlyAnUser(userDTO)) {
            toppingDTO.setUserEntered(true);
            toppingDTO.setAvailable(false);
            toppingDTO.setStatusEnum(StatusEnum.INSERITO);
            toppingDTO.setName(stringUtility.formatAllLower(toppingDTO.getName()).concat(" by ").concat(userDTO.getUsername()));
        } else {
            toppingDTO.setUserEntered(false);
            toppingDTO.setAvailable(true);
            toppingDTO.setStatusEnum(StatusEnum.DISPONIBILE);
            toppingDTO.setName(stringUtility.formatAllLower(toppingDTO.getName()));
        }

        if(privateFindByName(toppingDTO.getName()).isPresent()){
            final String resultString = "Topping".concat(toppingDTO.getName()).concat(" is already present!");
            log.error("ToppingServiceImpl - insert() -> {}", resultString);
            throw new MagicbusException(BeErrorCodeEnum.TOPPING_IS_ALREADY_PRESENT, resultString);
        }

        toppingDTO.setToppingDescription(stringUtility.formatAllLower(toppingDTO.getToppingDescription()));
        toppingDTO.setUserCreator(userDTO);
        log.info("ToppingServiceImpl - insert() -> Saving the topping..");
        return mapperFactory.getToppingMapper().save(toppingDTO)
                .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
    }

    private Optional<ToppingDTO> privateFindByName(final String toppingName) {
        log.info("ToppingServiceImpl - privateFindByName() -> Checking if topping name is null..");
        final String tn = Optional.ofNullable(toppingName)
                .map(stringUtility::formatAllLower)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.NAME_IS_NULL));
        log.info("ToppingServiceImpl - privateFindByName() -> Finding topping named '{}'..", tn);

        return Optional.ofNullable(tn)
                .flatMap(n -> mapperFactory.getToppingMapper().findByName(n));
    }
}
