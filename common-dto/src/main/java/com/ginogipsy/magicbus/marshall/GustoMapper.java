package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.dto.GustoDTO;
import com.ginogipsy.magicbus.repository.GustoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GustoMapper {

    private final ModelMapper modelMapper;
    private final GustoRepository gustoRepository;

    public GustoMapper(ModelMapper modelMapper, GustoRepository gustoRepository) {
        this.modelMapper = modelMapper;
        this.gustoRepository = gustoRepository;
    }

    public GustoDTO findByNome(final String nome){
        return convertToDTO(gustoRepository.findByNome(nome));
    }

    public List<GustoDTO> findByStatus(final String status) {
        final List<GustoDTO> gusti = new ArrayList<>();
        gustoRepository.findByStatus(Status.valueOf(status)).forEach(gusto -> {
            GustoDTO g = convertToDTO(gusto);
            gusti.add(g);
        });

        return gusti;
    }

    public List<GustoDTO> findByBase(final String base) {
        final List<GustoDTO> gusti = new ArrayList<>();
        gustoRepository.findByBase(Base.valueOf(base)).forEach(gusto -> {
            GustoDTO g = convertToDTO(gusto);
            gusti.add(g);
        });

        return gusti;
    }

    public List<GustoDTO> findByPeriodoDisponibilita(final String periodoDisponibilita) {
        final List<GustoDTO> gusti = new ArrayList<>();
        gustoRepository.findByPeriodoDisponibilita(PeriodoDisponibilita.valueOf(periodoDisponibilita)).forEach(gusto -> {
            GustoDTO g = convertToDTO(gusto);
            gusti.add(g);
        });

        return gusti;
    }

    public List<GustoDTO> findByCategoriaProdotto(final String categoriaProdotto) {
        final List<GustoDTO> gusti = new ArrayList<>();
        gustoRepository.findByCategoriaProdotto(CategoriaProdotto.valueOf(categoriaProdotto)).forEach(gusto -> {
            GustoDTO g = convertToDTO(gusto);
            gusti.add(g);
        });

        return gusti;
    }

    public List<GustoDTO> findByDisponibilita(final Boolean disponibile, final String status) {
        final List<GustoDTO> gusti = new ArrayList<>();
        gustoRepository.findByDisponibileAndStatus(disponibile, Status.valueOf(status)).forEach(gusto -> {
            GustoDTO g = convertToDTO(gusto);
            gusti.add(g);
        });

        return gusti;
    }

    public List<GustoDTO> findByDisponibileAndPeriodoDisponibilita(final Boolean disponibile, final String periodoDisponibilita) {
        final List<GustoDTO> gusti = new ArrayList<>();
        gustoRepository.findByDisponibileAndPeriodoDisponibilita(disponibile, PeriodoDisponibilita.valueOf(periodoDisponibilita)).forEach(gusto -> {
            GustoDTO g = convertToDTO(gusto);
            gusti.add(g);
        });

        return gusti;
    }

    public List<GustoDTO> findByInseritaDaUtente(final Boolean inseritaDaUtente) {
        final List<GustoDTO> gusti = new ArrayList<>();
        gustoRepository.findByGustoUtente_InseritaDaUtente(inseritaDaUtente).forEach(gusto -> {
            GustoDTO g = convertToDTO(gusto);
            gusti.add(g);
        });

        return gusti;
    }

    public List<GustoDTO> findByInseritaDaUtenteAndStatus(final Boolean inseritaDaUtente, final String status) {
        final List<GustoDTO> gusti = new ArrayList<>();
        gustoRepository.findByGustoUtente_InseritaDaUtenteAndStatus(inseritaDaUtente, Status.valueOf(status)).forEach(gusto -> {
            GustoDTO g = convertToDTO(gusto);
            gusti.add(g);
        });

        return gusti;
    }

    public List<GustoDTO> findByTipologiaMenuAndDisponibile(final String tipologiaMenu, final Boolean disponibile) {
        final List<GustoDTO> gusti = new ArrayList<>();
        gustoRepository.findByTipologiaMenuAndDisponibile(TipologiaMenu.valueOf(tipologiaMenu), disponibile).forEach(gusto -> {
            GustoDTO g = convertToDTO(gusto);
            gusti.add(g);
        });

        return gusti;
    }

    public GustoDTO convertToDTO(final Gusto gusto){
        return (gusto != null) ? modelMapper.map(gusto, GustoDTO.class) : null;
    }
}
