package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.customexception.notfound.GustoNotFoundException;
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

import javax.swing.text.html.Option;
import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return Optional.ofNullable(convertToDTO(gustoRepository.findByNome(nome)))
                .orElseThrow(() -> new GustoNotFoundException("il gusto non ha prodotto risultati!"));
    }

    public List<GustoDTO> findByStatus(final String status) {
        return gustoRepository.findByStatus(Status.getStatus(status)).stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByBase(final String base) {
        return gustoRepository.findByBase(Base.valueOf(base)).stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByPeriodoDisponibilita(final String periodoDisponibilita) {
        return gustoRepository.findByPeriodoDisponibilita(PeriodoDisponibilita.getPeriodoDisponibilita(periodoDisponibilita))
                .stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByCategoriaProdotto(final String categoriaProdotto) {
        return gustoRepository.findByCategoriaProdotto(CategoriaProdotto.valueOf(categoriaProdotto))
                .stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByDisponibilita(final boolean disponibile, final String status) {
        return gustoRepository.findByDisponibileAndStatus(disponibile, Status.getStatus(status))
                .stream().map(this::convertToDTO).toList();
    }

    public List<GustoDTO> findByDisponibileAndPeriodoDisponibilita(final boolean disponibile, final String periodoDisponibilita) {
        return gustoRepository.findByDisponibileAndPeriodoDisponibilita(disponibile, PeriodoDisponibilita.valueOf(periodoDisponibilita))
                .stream().map(this::convertToDTO).toList();
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
