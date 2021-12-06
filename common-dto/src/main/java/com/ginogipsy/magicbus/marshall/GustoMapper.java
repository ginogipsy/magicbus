package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.domain.enums.*;
import com.ginogipsy.magicbus.dto.GustoDTO;
import com.ginogipsy.magicbus.repository.GustoRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;
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

    public List<GustoDTO> findByNomeContains(final String nome){
        return gustoRepository.findByNomeContains(nome).stream().map(this::convertToDTO).toList();
    }

    public GustoDTO findByNome(final String nome){
        return convertToDTO(gustoRepository.findByNome(nome));
    }

    public List<GustoDTO> findByStatus(final String status) {
        return gustoRepository.findByStatus(Status.getStatus(status)).stream().map(this::convertToDTO).toList();
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

    public List<GustoDTO> findByTipologiaMenuAndDisponibile(final String tipologiaMenu, final Boolean disponibile) {
        final List<GustoDTO> gusti = new ArrayList<>();
        gustoRepository.findByTipologiaMenuAndDisponibile(TipologiaMenu.valueOf(tipologiaMenu), disponibile).forEach(gusto -> {
            GustoDTO g = convertToDTO(gusto);
            gusti.add(g);
        });

        return gusti;
    }

    public List<GustoDTO> findByInseritaDaUtente(boolean inseritaDaUtente){
        return gustoRepository.findByGustoUtente(inseritaDaUtente).stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByInseritaDaUtenteAndStatus(boolean inseritaDaUtente, String status){
        return gustoRepository.findByGustoUtenteAndStatus(inseritaDaUtente, Status.getStatus(status)).stream().map(this::convertToDTO).toList();
    }



    public GustoDTO convertToDTO(final Gusto gusto){
        return (gusto != null) ? modelMapper.map(gusto, GustoDTO.class) : null;
    }

    public Gusto convertToEntity(final GustoDTO gustoDTO){
        return (gustoDTO != null) ? modelMapper.map(gustoDTO, Gusto.class) : null;
    }

    public GustoDTO save(final GustoDTO gustoDTO){
        return convertToDTO(gustoRepository.save(convertToEntity(gustoDTO)));
    }
}
