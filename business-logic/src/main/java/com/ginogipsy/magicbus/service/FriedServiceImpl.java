package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.UserUtility;
import com.ginogipsy.magicbus.domain.enums.StatusEnum;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
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
public class FriedServiceImpl implements FriedService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;
    private final UserUtility userUtility;

    @Override
    public FriedDTO insert(final FriedDTO friedDTO, final UserDTO userDTO) {
        if(userUtility.isOnlyAnUser(userDTO)){
            friedDTO.setUserEntered(true);
            friedDTO.setAvailable(false);
            friedDTO.setStatusEnum(StatusEnum.INSERITO);
            friedDTO.setName(stringUtility.formatAllLower(friedDTO.getName()).concat(" by ").concat(userDTO.getUsername()));
        }else{
            friedDTO.setUserEntered(false);
            friedDTO.setAvailable(true);
            friedDTO.setStatusEnum(StatusEnum.DISPONIBILE);
            friedDTO.setName(stringUtility.formatAllLower(friedDTO.getName()));
        }

        if(mapperFactory.getFriedMapper().findByName(friedDTO.getName()).isPresent()){
            log.info("AllergenServiceImpl - insert() -> Checking if allergen is null..");
            throw new MagicbusException(BeErrorCodeEnum.FRIED_IS_ALREADY_PRESENT, "Fried ".concat(friedDTO.getName()).concat(" is already present!"));
        }

        friedDTO.setFriedDescription(stringUtility.formatAllLower(friedDTO.getFriedDescription()));
        friedDTO.setUserCreator(userDTO);
        return mapperFactory.getFriedMapper().save(friedDTO);
    }

    @Override
    public FriedDTO findByName(final String friedName) {
        stringUtility.formatAllLower(friedName);
        log.info("FriedServiceImpl - findByName() -> Finding fried named '{}'..", friedName);
        return mapperFactory.getFriedMapper()
                .findByName(friedName)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.FRIED_NOT_FOUND, "Fried "+friedName+" not found!"));
    }

    @Override
    public List<FriedDTO> findByStatus(final String status) {
        return mapperFactory.getFriedMapper().findByStatus(status);
    }
}
