package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.UserUtility;
import com.ginogipsy.magicbus.customexception.gusto.GustoIsPresentException;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.dto.FrittoDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import org.springframework.stereotype.Service;

@Service
public class FrittoServiceImpl implements FrittoService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;
    private final UserUtility userUtility;

    public FrittoServiceImpl(MapperFactory mapperFactory, StringUtility stringUtility, UserUtility userUtility) {
        this.mapperFactory = mapperFactory;
        this.stringUtility = stringUtility;
        this.userUtility = userUtility;
    }


    @Override
    public FrittoDTO insertFritto(FrittoDTO frittoDTO, UserDTO userDTO) {
        if(userUtility.isOnlyAnUser(userDTO)){
            frittoDTO.setFrittoUtente(true);
            frittoDTO.setDisponibile(false);
            frittoDTO.setStatus(Status.INSERITO);
            frittoDTO.setNome(stringUtility.formattataMinuscConSpaziaturaCorretta(frittoDTO.getNome()).concat(" by ").concat(userDTO.getUsername()));
        }else{
            frittoDTO.setFrittoUtente(false);
            frittoDTO.setDisponibile(true);
            frittoDTO.setStatus(Status.DISPONIBILE);
            frittoDTO.setNome(stringUtility.formattataMinuscConSpaziaturaCorretta(frittoDTO.getNome()));
        }

        if(mapperFactory.getFrittoMapper().findByName(frittoDTO.getNome()) != null){
            throw new GustoIsPresentException("Il gusto ".concat(frittoDTO.getNome()).concat(" è già presente!"));
        }

        frittoDTO.setDescrizioneFritto(stringUtility.formattataMinuscConSpaziaturaCorretta(frittoDTO.getDescrizioneFritto()));
        frittoDTO.setUserCreator(userDTO);
        return mapperFactory.getFrittoMapper().save(frittoDTO);
    }
}
