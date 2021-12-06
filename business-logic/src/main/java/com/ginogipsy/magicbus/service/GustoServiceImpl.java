package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.customexception.gusto.GustoIsPresentException;
import com.ginogipsy.magicbus.customexception.notfound.*;
import com.ginogipsy.magicbus.domain.enums.Status;
import com.ginogipsy.magicbus.dto.GustoDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GustoServiceImpl implements GustoService {

    private final MapperFactory mapperFactory;
    private final StringUtility stringUtility;

    public GustoServiceImpl(MapperFactory mapperFactory, StringUtility stringUtility) {
        this.mapperFactory = mapperFactory;
        this.stringUtility = stringUtility;
    }

    @Override
    public List<GustoDTO> findGustiByStatus(final String status) throws StatusProductsNotFoundException {
           return mapperFactory.getGustoMapper().findByStatus(status);
    }

    @Override
    public GustoDTO findGustoByNome(final String nomeGusto) throws GustoNotFoundException {
        final String n = Optional.ofNullable(stringUtility.formattataMinuscConSpaziaturaCorretta(nomeGusto))
                .orElseThrow(() -> new GustoNotFoundException("nomeGusto risulta NULL!"));
        return Optional.ofNullable(mapperFactory.getGustoMapper()
                .findByNome(n)).orElseThrow(() -> new GustoNotFoundException("Gusto "+nomeGusto+" non trovato!"));
    }

    @Override
    public List<GustoDTO> findGustiNameContains(final String nomeGusto) throws GustoNotFoundException {
        final String n = Optional.ofNullable(stringUtility.formattataMinuscConSpaziaturaCorretta(nomeGusto))
                .orElseThrow(() -> new GustoNotFoundException("nomeGusto risulta NULL!"));
        return mapperFactory.getGustoMapper().findByNomeContains(n);
    }

    @Override
    public List<GustoDTO> findByBase(final String base) throws BaseNotFoundException {
        return mapperFactory.getGustoMapper().findByBase(base);
    }

    @Override
    public List<GustoDTO> findByCategoriaProdotto(String categoriaProdotto) {
        return mapperFactory.getGustoMapper().findByCategoriaProdotto(categoriaProdotto);
    }

    @Override
    public List<GustoDTO> findByPeriodoDisponibilita(String periodoDisponibilita) {
        return mapperFactory.getGustoMapper().findByPeriodoDisponibilita(periodoDisponibilita);
    }

    @Override
    public List<GustoDTO> findByDisponibilita(boolean disponibile, String status) {
        return mapperFactory.getGustoMapper().findByDisponibilita(disponibile, status);
    }

    @Override
    public List<GustoDTO> findByDisponibilitaAndPeriodoDisponibilita(boolean disponibile, String periodoDisponibilita) {
        return mapperFactory.getGustoMapper().findByDisponibileAndPeriodoDisponibilita(disponibile, periodoDisponibilita);
    }

    @Override
    public List<GustoDTO> findByInseritaDaUtente(boolean inseritaDaUtente) {
        return mapperFactory.getGustoMapper().findByInseritaDaUtente(inseritaDaUtente);
    }

    @Override
    public List<GustoDTO> findByInseritaDaUtenteAndStatus(boolean inseritaDaUtente, String status) {
        return mapperFactory.getGustoMapper().findByInseritaDaUtenteAndStatus(inseritaDaUtente, status);
    }

    @Override
    public GustoDTO insertGusto(GustoDTO gustoDTO, UserDTO userDTO){
        if(userDTO.getRoles().size() == 1 && userDTO.getRoles().contains(mapperFactory.getRoleMapper().findByProfilo("USER"))){
            gustoDTO.setGustoUtente(true);
            gustoDTO.setDisponibile(false);
            gustoDTO.setStatus(Status.INSERITO);
            gustoDTO.setNome(stringUtility.formattataMinuscConSpaziaturaCorretta(gustoDTO.getNome()).concat(" by ").concat(userDTO.getUsername()));
        }else{
            gustoDTO.setGustoUtente(false);
            gustoDTO.setDisponibile(true);
            gustoDTO.setStatus(Status.DISPONIBILE);
            gustoDTO.setNome(stringUtility.formattataMinuscConSpaziaturaCorretta(gustoDTO.getNome()));
        }

        if(mapperFactory.getGustoMapper().findByNome(gustoDTO.getNome()) != null){
            throw new GustoIsPresentException("Il gusto ".concat(gustoDTO.getNome()).concat(" è già presente!"));
        }

        gustoDTO.setDescrizioneGusto(stringUtility.formattataMinuscConSpaziaturaCorretta(gustoDTO.getDescrizioneGusto()));
        gustoDTO.setUserCreator(userDTO);
        return mapperFactory.getGustoMapper().save(gustoDTO);

    }
}
