package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.utilityenum.*;
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
    private final StatusUtility statusUtility;
    private final BaseUtility baseUtility;
    private final CategoriaProdottoUtility categoriaProdottoUtility;
    private final PeriodoDisponibilitaUtility periodoDisponibilitaUtility;
    private final StringUtility stringUtility;
    private final TipologiaMenuUtility tipologiaMenuUtility;

    public GustoServiceImpl(MapperFactory mapperFactory, StatusUtility statusUtility, BaseUtility baseUtility, CategoriaProdottoUtility categoriaProdottoUtility, PeriodoDisponibilitaUtility periodoDisponibilitaUtility, StringUtility stringUtility, TipologiaMenuUtility tipologiaMenuUtility) {
        this.mapperFactory = mapperFactory;
        this.statusUtility = statusUtility;
        this.baseUtility = baseUtility;
        this.categoriaProdottoUtility = categoriaProdottoUtility;
        this.periodoDisponibilitaUtility = periodoDisponibilitaUtility;
        this.stringUtility = stringUtility;
        this.tipologiaMenuUtility = tipologiaMenuUtility;
    }

    @Override
    public List<GustoDTO> findGustiByStatus(final String status) throws StatusProductsNotFoundException {
           return mapperFactory.getGustoMapper().findByStatus(status);
    }

    @Override
    public GustoDTO findGustoByNome(final String nomeGusto) throws GustoNotFoundException {
        final String n = Optional.ofNullable(stringUtility.formattataMinuscConSpaziaturaCorretta(nomeGusto))
                .orElseThrow(() -> new GustoNotFoundException("nomeGusto risulta NULL!"));
        return Optional.ofNullable(mapperFactory.getGustoMapper().findByNome(n)).orElseThrow(() -> new GustoNotFoundException("Gusto "+nomeGusto+" non trovato!"));
    }

    @Override
    public List<GustoDTO> findGustiNameContains(final String nomeGusto) throws GustoNotFoundException {
        final String n = Optional.ofNullable(stringUtility.formattataMinuscConSpaziaturaCorretta(nomeGusto))
                .orElseThrow(() -> new GustoNotFoundException("nomeGusto risulta NULL!"));
        return mapperFactory.getGustoMapper().findByNomeContains(n);
    }

    @Override
    public List<GustoDTO> findByBase(final String base) throws BaseNotFoundException {
        return mapperFactory.getGustoMapper().findByBase(baseUtility.verifyBase(base));
    }

    @Override
    public List<GustoDTO> findByCategoriaProdotto(String categoriaProdotto) throws CategoriaProdottoNotFoundException {
        return mapperFactory.getGustoMapper().findByCategoriaProdotto(categoriaProdottoUtility.verifyCategoriaProdotto(categoriaProdotto));
    }

    @Override
    public List<GustoDTO> findByPeriodoDisponibilita(String periodoDisponibilita) throws PeriodoDisponibilitaNotFoundException {
        return mapperFactory.getGustoMapper().findByPeriodoDisponibilita(periodoDisponibilitaUtility.verifyPeriodoDisponibilita(periodoDisponibilita));
    }

    @Override
    public List<GustoDTO> findByDisponibilita(Boolean disponibile, String status) throws StatusProductsNotFoundException {
        return mapperFactory.getGustoMapper().findByDisponibilita(verifyDisponibilita(disponibile), statusUtility.statusVerify(status));
    }

    @Override
    public List<GustoDTO> findByDisponibilitaAndPeriodoDisponibilita(Boolean disponibile, String periodoDisponibilita) throws PeriodoDisponibilitaNotFoundException {
        return mapperFactory.getGustoMapper().findByDisponibileAndPeriodoDisponibilita(verifyDisponibilita(disponibile), periodoDisponibilitaUtility.verifyPeriodoDisponibilita(periodoDisponibilita));
    }

    @Override
    public List<GustoDTO> findByInseritaDaUtente(Boolean inseritaDaUtente) {
        return mapperFactory.getGustoMapper().findByInseritaDaUtente(Optional.ofNullable(inseritaDaUtente).orElseThrow(() -> new NullPointerException("Non risulta inserita la scelta: Inserito Da Utente!")));
    }

    @Override
    public List<GustoDTO> findByInseritaDaUtenteAndStatus(Boolean inseritaDaUtente, String status) throws StatusProductsNotFoundException {
        return mapperFactory.getGustoMapper().findByInseritaDaUtenteAndStatus(Optional.ofNullable(inseritaDaUtente).orElseThrow(() -> new NullPointerException("Non risulta inserita la scelta: Inserito Da Utente!")), status);
    }

    @Override
    public List<GustoDTO> findByTipologiaMenuAndDisponibile(String tipologiaMenu, Boolean disponibile) throws TipologiaMenuNotFoundException {
        return mapperFactory.getGustoMapper().findByTipologiaMenuAndDisponibile(tipologiaMenuUtility.verifytipologiaMenu(tipologiaMenu), verifyDisponibilita(disponibile));
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


    private Boolean verifyDisponibilita(final Boolean disponibile){
        return Optional.ofNullable(disponibile).orElseThrow(() -> new NullPointerException("Non risulta inserita la scelta della disponibilità!"));
    }
}
