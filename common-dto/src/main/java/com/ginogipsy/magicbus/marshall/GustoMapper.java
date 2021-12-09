package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.*;
import com.ginogipsy.magicbus.domain.enums.*;
import com.ginogipsy.magicbus.dto.GustoDTO;
import com.ginogipsy.magicbus.repository.TasteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GustoMapper {

    private final ModelMapper modelMapper;
    private final TasteRepository tasteRepository;

    public GustoMapper(ModelMapper modelMapper, TasteRepository tasteRepository) {
        this.modelMapper = modelMapper;
        this.tasteRepository = tasteRepository;
    }

    public List<GustoDTO> findByNomeContains(final String nome){
        return tasteRepository.findByNomeContains(nome).stream().map(this::convertToDTO).toList();
    }

    public GustoDTO findByNome(final String nome){
        return convertToDTO(tasteRepository.findByNome(nome));
    }

    public List<GustoDTO> findByStatus(final String status) {
        return tasteRepository.findByStatus(Status.getStatus(status)).stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByBase(final String base) {
        return tasteRepository.findByBase(Base.valueOf(base)).stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByPeriodoDisponibilita(final String periodoDisponibilita) {
        return tasteRepository.findByPeriodoDisponibilita(AvailabilityPeriod.getPeriodoDisponibilita(periodoDisponibilita))
                .stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByCategoriaProdotto(final String categoriaProdotto) {
        return tasteRepository.findByCategoriaProdotto(ProductCategory.valueOf(categoriaProdotto))
                .stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByDisponibilita(final boolean disponibile, final String status) {
        return tasteRepository.findByDisponibileAndStatus(disponibile, Status.getStatus(status))
                .stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByDisponibileAndPeriodoDisponibilita(final boolean disponibile, final String periodoDisponibilita) {
        return tasteRepository.findByDisponibileAndPeriodoDisponibilita(disponibile, AvailabilityPeriod.valueOf(periodoDisponibilita))
                .stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByInseritaDaUtente(boolean inseritaDaUtente){
        return tasteRepository.findByGustoUtente(inseritaDaUtente).stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByInseritaDaUtenteAndStatus(boolean inseritaDaUtente, String status){
        return tasteRepository.findByGustoUtenteAndStatus(inseritaDaUtente, Status.getStatus(status)).stream().map(this::convertToDTO).toList();
    }

    public GustoDTO convertToDTO(final Taste taste){
        return (taste != null) ? modelMapper.map(taste, GustoDTO.class) : null;
    }

    public Taste convertToEntity(final GustoDTO gustoDTO){
        return (gustoDTO != null) ? modelMapper.map(gustoDTO, Taste.class) : null;
    }

    public GustoDTO save(final GustoDTO gustoDTO){
        return convertToDTO(tasteRepository.save(convertToEntity(gustoDTO)));
    }
}
