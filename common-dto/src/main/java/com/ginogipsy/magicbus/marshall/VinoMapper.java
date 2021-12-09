package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.customexception.notfound.VinoNotFoundException;
import com.ginogipsy.magicbus.domain.enums.WineQuality;
import com.ginogipsy.magicbus.domain.Vino;
import com.ginogipsy.magicbus.dto.VinoDTO;
import com.ginogipsy.magicbus.repository.VinoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class VinoMapper {

    private final ModelMapper modelMapper;
    private final VinoRepository vinoRepository;

    public VinoMapper(ModelMapper modelMapper, VinoRepository vinoRepository) {
        this.modelMapper = modelMapper;
        this.vinoRepository = vinoRepository;
    }

    public Vino convertToEntity(VinoDTO vinoDTO){
        return (vinoDTO != null) ? modelMapper.map(vinoDTO, Vino.class) : null;
    }

    public VinoDTO convertToDTO(Vino vino){
        return (vino != null) ? modelMapper.map(vino, VinoDTO.class) : null;
    }

    public VinoDTO findByNome(String nome){
        return Optional.ofNullable(vinoRepository.findByNome(nome)).map(this::convertToDTO).orElseThrow(() -> new VinoNotFoundException("Vino non trovato"));
    }

    public List<VinoDTO> findByNomeCantina(String nomeCantina){
        return Optional.of(vinoRepository.findByCantina_Nome(nomeCantina).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini per questa cantina - "+nomeCantina+" - non trovati"));
    }

    public List<VinoDTO> findByDisponibile(boolean disponibile){
        return Optional.of(vinoRepository.findByDisponibile(disponibile).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini disponibili non trovati"));
    }

    public List<VinoDTO> findByQualitaVino(String qualitaVino){
        return Optional.of(vinoRepository.findByQualitaVino(WineQuality.valueOf(qualitaVino)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini per questa qualità - "+qualitaVino+" - non trovati"));
    }

    public List<VinoDTO> findByDisponibileAndCantinaNome(boolean disponibile, String nomeCantina){
        return Optional.of(vinoRepository.findByDisponibileAndCantina_Nome(disponibile,nomeCantina).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini disponibili per questa cantina - "+nomeCantina+" - non trovati"));
    }

    public List<VinoDTO> findByDisponibileAndQualitaVino(boolean disponibile, String qualitaVino){
        return Optional.of(vinoRepository.findByDisponibileAndQualitaVino(disponibile, WineQuality.valueOf(qualitaVino)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini disponibili per questa qualità - "+qualitaVino+" - non trovati"));
    }
}
