package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.UserUtility;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FriedServiceImpl implements FriedService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;
    private final UserUtility userUtility;

    public FriedServiceImpl(MapperFactory mapperFactory, StringUtility stringUtility, UserUtility userUtility) {
        this.mapperFactory = mapperFactory;
        this.stringUtility = stringUtility;
        this.userUtility = userUtility;
    }


    @Override
    public FriedDTO insertFried(FriedDTO friedDTO, UserDTO userDTO) {
        if(userUtility.isOnlyAnUser(userDTO)){
            friedDTO.setUserEntered(true);
            friedDTO.setAvailable(false);
            friedDTO.setStatus(Status.INSERITO);
            friedDTO.setName(stringUtility.formatAllMinusc(friedDTO.getName()).concat(" by ").concat(userDTO.getUsername()));
        }else{
            friedDTO.setUserEntered(false);
            friedDTO.setAvailable(true);
            friedDTO.setStatus(Status.DISPONIBILE);
            friedDTO.setName(stringUtility.formatAllMinusc(friedDTO.getName()));
        }

        if(mapperFactory.getFriedMapper().findByName(friedDTO.getName()) != null){
            log.error("Taste is already present on DB!");
            throw new MagicbusException(BeErrorCodeEnum.TASTE_IS_ALREADY_PRESENT, "Taste ".concat(friedDTO.getName()).concat(" is already present!"));
        }

        friedDTO.setFriedDescription(stringUtility.formatAllMinusc(friedDTO.getFriedDescription()));
        friedDTO.setUserCreator(userDTO);
        return mapperFactory.getFriedMapper().save(friedDTO);
    }

    @Override
    public FriedDTO findByName(String friedName) {
        log.info("Check if fried name is null..");
        final String n = Optional.ofNullable(stringUtility.formatAllMinusc(friedName))
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.TASTE_IS_NULL, "Taste name is null!"));
        return Optional.ofNullable(mapperFactory.getFriedMapper()
                .findByName(n)).orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.TASTE_NOT_FOUND, "Taste "+friedName+" not found!"));
    }

    @Override
    public List<FriedDTO> findByStatus(String status) {
        return mapperFactory.getFriedMapper().findByStatus(status).stream().toList();
    }
}
