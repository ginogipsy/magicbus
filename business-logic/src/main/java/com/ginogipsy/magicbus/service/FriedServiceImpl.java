package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.UserUtility;
import com.ginogipsy.magicbus.customexception.gusto.GustoIsPresentException;
import com.ginogipsy.magicbus.customexception.notfound.GustoNotFoundException;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            throw new GustoIsPresentException("Il gusto ".concat(friedDTO.getName()).concat(" è già presente!"));
        }

        friedDTO.setFriedDescription(stringUtility.formatAllMinusc(friedDTO.getFriedDescription()));
        friedDTO.setUserCreator(userDTO);
        return mapperFactory.getFriedMapper().save(friedDTO);
    }

    @Override
    public FriedDTO findByName(String friedName) {
        final String n = Optional.ofNullable(stringUtility.formatAllMinusc(friedName))
                .orElseThrow(() -> new GustoNotFoundException("nomeGusto risulta NULL!"));
        return Optional.ofNullable(mapperFactory.getFriedMapper()
                .findByName(n)).orElseThrow(() -> new GustoNotFoundException("Gusto "+friedName+" non trovato!"));
    }

    @Override
    public List<FriedDTO> findByStatus(String status) {
        return mapperFactory.getFriedMapper().findByStatus(status).stream().toList();
    }
}
