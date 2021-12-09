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
public class GustoServiceImpl implements GustoService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;
    private final UserUtility userUtility;

    public GustoServiceImpl(MapperFactory mapperFactory, StringUtility stringUtility, UserUtility userUtility) {
        this.mapperFactory = mapperFactory;
        this.stringUtility = stringUtility;
        this.userUtility = userUtility;
    }

    @Override
    public List<TasteDTO> findGustiByStatus(final String status) throws StatusProductsNotFoundException {
           return mapperFactory.getGustoMapper().findByStatus(status);
    }

    @Override
    public TasteDTO findGustoByNome(final String nomeGusto) throws GustoNotFoundException {
        final String n = Optional.ofNullable(stringUtility.formattataMinuscConSpaziaturaCorretta(nomeGusto))
                .orElseThrow(() -> new GustoNotFoundException("nomeGusto risulta NULL!"));
        return Optional.ofNullable(mapperFactory.getGustoMapper()
                .findByNome(n)).orElseThrow(() -> new GustoNotFoundException("Gusto "+nomeGusto+" non trovato!"));
    }

    @Override
    public List<TasteDTO> findGustiNameContains(final String nomeGusto) throws GustoNotFoundException {
        final String n = Optional.ofNullable(stringUtility.formattataMinuscConSpaziaturaCorretta(nomeGusto))
                .orElseThrow(() -> new GustoNotFoundException("nomeGusto risulta NULL!"));
        return mapperFactory.getGustoMapper().findByNomeContains(n);
    }

    @Override
    public List<TasteDTO> findByBase(final String base) throws BaseNotFoundException {
        return mapperFactory.getGustoMapper().findByBase(base);
    }

    @Override
    public List<TasteDTO> findByCategoriaProdotto(String categoriaProdotto) {
        return mapperFactory.getGustoMapper().findByCategoriaProdotto(categoriaProdotto);
    }

    @Override
    public List<TasteDTO> findByPeriodoDisponibilita(String periodoDisponibilita) {
        return mapperFactory.getGustoMapper().findByPeriodoDisponibilita(periodoDisponibilita);
    }

    @Override
    public List<TasteDTO> findByDisponibilita(boolean disponibile, String status) {
        return mapperFactory.getGustoMapper().findByDisponibilita(disponibile, status);
    }

    @Override
    public List<TasteDTO> findByDisponibilitaAndPeriodoDisponibilita(boolean disponibile, String periodoDisponibilita) {
        return mapperFactory.getGustoMapper().findByDisponibileAndPeriodoDisponibilita(disponibile, periodoDisponibilita);
    }

    @Override
    public List<TasteDTO> findByInseritaDaUtente(boolean inseritaDaUtente) {
        return mapperFactory.getGustoMapper().findByInseritaDaUtente(inseritaDaUtente);
    }

    @Override
    public List<TasteDTO> findByInseritaDaUtenteAndStatus(boolean inseritaDaUtente, String status) {
        return mapperFactory.getGustoMapper().findByInseritaDaUtenteAndStatus(inseritaDaUtente, status);
    }

    @Override
    public TasteDTO insertGusto(TasteDTO tasteDTO, UserDTO userDTO){
        if(userUtility.isOnlyAnUser(userDTO)){
            tasteDTO.setGustoUtente(true);
            tasteDTO.setDisponibile(false);
            tasteDTO.setStatus(Status.INSERITO);
            tasteDTO.setNome(stringUtility.formattataMinuscConSpaziaturaCorretta(tasteDTO.getNome()).concat(" by ").concat(userDTO.getUsername()));
        }else{
            tasteDTO.setGustoUtente(false);
            tasteDTO.setDisponibile(true);
            tasteDTO.setStatus(Status.DISPONIBILE);
            tasteDTO.setNome(stringUtility.formattataMinuscConSpaziaturaCorretta(tasteDTO.getNome()));
        }

        if(mapperFactory.getGustoMapper().findByNome(tasteDTO.getNome()) != null){
            throw new GustoIsPresentException("Il gusto ".concat(tasteDTO.getNome()).concat(" è già presente!"));
        }

        tasteDTO.setDescrizioneGusto(stringUtility.formattataMinuscConSpaziaturaCorretta(tasteDTO.getDescrizioneGusto()));
        tasteDTO.setUserCreator(userDTO);
        return mapperFactory.getGustoMapper().save(tasteDTO);
    }
}
