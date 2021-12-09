package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.customexception.notfound.VinoNotFoundException;
import com.ginogipsy.magicbus.domain.enums.WineQuality;
import com.ginogipsy.magicbus.domain.Wine;
import com.ginogipsy.magicbus.dto.WineDTO;
import com.ginogipsy.magicbus.repository.WineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class WineMapper {

    private final ModelMapper modelMapper;
    private final WineRepository wineRepository;

    public WineMapper(ModelMapper modelMapper, WineRepository wineRepository) {
        this.modelMapper = modelMapper;
        this.wineRepository = wineRepository;
    }

    public Wine convertToEntity(WineDTO wineDTO){
        return (wineDTO != null) ? modelMapper.map(wineDTO, Wine.class) : null;
    }

    public WineDTO convertToDTO(Wine wine){
        return (wine != null) ? modelMapper.map(wine, WineDTO.class) : null;
    }

    public WineDTO findByName(String name){
        return Optional.ofNullable(wineRepository.findByName(name)).map(this::convertToDTO).orElseThrow(() -> new VinoNotFoundException("Vino non trovato"));
    }

    public List<WineDTO> findByWineryName(String wineryName){
        return Optional.of(wineRepository.findByWinery_Name(wineryName).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini per questa cantina - "+wineryName+ " - non sono disponibili"));
    }

    public List<WineDTO> findByAvailable(boolean available){
        return Optional.of(wineRepository.findByAvailable(available).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini disponibili non trovati"));
    }

    public List<WineDTO> findByWineQuality(String wineQuality){
        return Optional.of(wineRepository.findByWineQuality(WineQuality.valueOf(wineQuality)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini per questa qualità - "+wineQuality+" - non disponibili"));
    }

    public List<WineDTO> findByAvailableAndWineryName(boolean available, String wineryName){
        return Optional.of(wineRepository.findByAvailableAndWinery_Name(available,wineryName).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini disponibili per questa cantina - "+wineryName+" - non trovati"));
    }

    public List<WineDTO> findByAvailableAndWineQuality(boolean available, String wineQuality){
        return Optional.of(wineRepository.findByAvailableAndWineQuality(available, WineQuality.valueOf(wineQuality)).stream().map(this::convertToDTO).collect(Collectors.toList())).orElseThrow(() -> new VinoNotFoundException("Vini disponibili per questa qualità - "+wineQuality+" - non trovati"));
    }
}
