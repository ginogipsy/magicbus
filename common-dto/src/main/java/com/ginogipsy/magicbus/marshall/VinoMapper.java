package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.customexception.notfound.VinoNotFoundException;
import com.ginogipsy.magicbus.domain.enums.WineQuality;
import com.ginogipsy.magicbus.domain.Wine;
import com.ginogipsy.magicbus.dto.VinoDTO;
import com.ginogipsy.magicbus.repository.WineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class VinoMapper {

    private final ModelMapper modelMapper;
    private final WineRepository wineRepository;

    public VinoMapper(ModelMapper modelMapper, WineRepository wineRepository) {
        this.modelMapper = modelMapper;
        this.wineRepository = wineRepository;
    }

    public Wine convertToEntity(VinoDTO vinoDTO){
        return (vinoDTO != null) ? modelMapper.map(vinoDTO, Wine.class) : null;
    }

    public VinoDTO convertToDTO(Wine wine){
        return (wine != null) ? modelMapper.map(wine, VinoDTO.class) : null;
    }

    public VinoDTO findByNome(String nome){
        return Optional.ofNullable(wineRepository.findByNome(nome)).map(this::convertToDTO).orElseThrow(() -> new VinoNotFoundException("Vino non trovato"));
    }

    public List<VinoDTO> findByNomeCantina(String nomeCantina){
        return Optional.of(wineRepository.findByCantina_Nome(nomeCantina).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini per questa cantina - "+nomeCantina+" - non trovati"));
    }

    public List<VinoDTO> findByDisponibile(boolean disponibile){
        return Optional.of(wineRepository.findByDisponibile(disponibile).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini disponibili non trovati"));
    }

    public List<VinoDTO> findByQualitaVino(String qualitaVino){
        return Optional.of(wineRepository.findByQualitaVino(WineQuality.valueOf(qualitaVino)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini per questa qualità - "+qualitaVino+" - non trovati"));
    }

    public List<VinoDTO> findByDisponibileAndCantinaNome(boolean disponibile, String nomeCantina){
        return Optional.of(wineRepository.findByDisponibileAndCantina_Nome(disponibile,nomeCantina).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini disponibili per questa cantina - "+nomeCantina+" - non trovati"));
    }

    public List<VinoDTO> findByDisponibileAndQualitaVino(boolean disponibile, String qualitaVino){
        return Optional.of(wineRepository.findByDisponibileAndQualitaVino(disponibile, WineQuality.valueOf(qualitaVino)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini disponibili per questa qualità - "+qualitaVino+" - non trovati"));
    }
}
