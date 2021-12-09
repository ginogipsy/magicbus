package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.UserUtility;
import com.ginogipsy.magicbus.customexception.gusto.GustoIsPresentException;
import com.ginogipsy.magicbus.customexception.notfound.*;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.dto.TasteDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasteServiceImpl implements TasteService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;
    private final UserUtility userUtility;

    public TasteServiceImpl(MapperFactory mapperFactory, StringUtility stringUtility, UserUtility userUtility) {
        this.mapperFactory = mapperFactory;
        this.stringUtility = stringUtility;
        this.userUtility = userUtility;
    }

    @Override
    public List<TasteDTO> findByStatus(final String status) throws StatusProductsNotFoundException {
           return mapperFactory.getTasteMapper().findByStatus(status);
    }

    @Override
    public TasteDTO findByName(final String tasteName) throws GustoNotFoundException {
        final String n = Optional.ofNullable(stringUtility.formatAllMinusc(tasteName))
                .orElseThrow(() -> new GustoNotFoundException("nomeGusto risulta NULL!"));
        return Optional.ofNullable(mapperFactory.getTasteMapper()
                .findByName(n)).orElseThrow(() -> new GustoNotFoundException("Gusto "+tasteName+" non trovato!"));
    }

    @Override
    public List<TasteDTO> findTasteWhereNamesContains(final String tasteName) throws GustoNotFoundException {
        final String n = Optional.ofNullable(stringUtility.formatAllMinusc(tasteName))
                .orElseThrow(() -> new GustoNotFoundException("nomeGusto risulta NULL!"));
        return mapperFactory.getTasteMapper().findByNomeContains(n);
    }

    @Override
    public List<TasteDTO> findByBase(final String base) throws BaseNotFoundException {
        return mapperFactory.getTasteMapper().findByBase(base);
    }

    @Override
    public List<TasteDTO> findByProductCategory(String productCategory) {
        return mapperFactory.getTasteMapper().findByProductCategory(productCategory);
    }

    @Override
    public List<TasteDTO> findByAvailabilityPeriod(String availabilityPeriod) {
        return mapperFactory.getTasteMapper().findByAvailabilityPeriod(availabilityPeriod);
    }

    @Override
    public List<TasteDTO> findByAvailabilityAndStatus(boolean available, String status) {
        return mapperFactory.getTasteMapper().findByAvailableAndStatus(available, status);
    }

    @Override
    public List<TasteDTO> findByAvailableAndAvailabilityPeriod(boolean available, String availabilityPeriod) {
        return mapperFactory.getTasteMapper().findByAvailableAndAvailabilityPeriod(available, availabilityPeriod);
    }

    @Override
    public List<TasteDTO> findByUserEntered(boolean userEntered) {
        return mapperFactory.getTasteMapper().findByUserEntered(userEntered);
    }

    @Override
    public List<TasteDTO> findByUserEnteredAndStatus(boolean userEntered, String status) {
        return mapperFactory.getTasteMapper().findByUserEnteredAndStatus(userEntered, status);
    }

    @Override
    public TasteDTO insertTaste(TasteDTO tasteDTO, UserDTO userDTO){
        if(userUtility.isOnlyAnUser(userDTO)){
            tasteDTO.setUserEntered(true);
            tasteDTO.setAvailable(false);
            tasteDTO.setStatus(Status.INSERITO);
            tasteDTO.setName(stringUtility.formatAllMinusc(tasteDTO.getName()).concat(" by ").concat(userDTO.getUsername()));
        }else{
            tasteDTO.setUserEntered(false);
            tasteDTO.setAvailable(true);
            tasteDTO.setStatus(Status.DISPONIBILE);
            tasteDTO.setName(stringUtility.formatAllMinusc(tasteDTO.getName()));
        }

        if(mapperFactory.getTasteMapper().findByName(tasteDTO.getName()) != null){
            throw new GustoIsPresentException("Il gusto ".concat(tasteDTO.getName()).concat(" è già presente!"));
        }

        tasteDTO.setTasteDescription(stringUtility.formatAllMinusc(tasteDTO.getTasteDescription()));
        tasteDTO.setUserCreator(userDTO);
        return mapperFactory.getTasteMapper().save(tasteDTO);
    }
}
