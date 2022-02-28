package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Wine;
import com.ginogipsy.magicbus.domain.enums.WineQuality;
import com.ginogipsy.magicbus.dto.WineDTO;
import com.ginogipsy.magicbus.repository.WineRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class WineMapper {

    private final ModelMapper modelMapper;
    private final WineRepository wineRepository;

    public WineMapper(ModelMapper modelMapper, WineRepository wineRepository) {
        this.modelMapper = modelMapper;
        this.wineRepository = wineRepository;
    }

    public Wine convertToEntity(final WineDTO wineDTO){
        return Optional.ofNullable(wineDTO)
                .map(w -> modelMapper.map(w, Wine.class))
            .orElse(null);
    }

    public WineDTO convertToDTO(final Wine wine){
        return Optional.ofNullable(wine)
                .map(w -> modelMapper.map(w, WineDTO.class))
            .orElse(null);
    }

    public WineDTO findByName(final String name){
        log.info("Searching wines where name is " + name+ "..");
        return Optional.ofNullable(name)
                .map(wineRepository::findByName)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<WineDTO> findByWineryName(final String wineryName){
        log.info("Searching wines where winery name is " + wineryName+ "..");
        return Optional.ofNullable(wineryName)
                .map(w -> wineRepository.findByWinery_Name(w)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<WineDTO> findByAvailable(final boolean available){
        log.info("Searching wine where availability is " + available+ "..");
        return wineRepository.findByAvailable(available)
                .stream()
                .map(this::convertToDTO)
                .toList();
        }

    public List<WineDTO> findByWineQuality(final String wineQuality){
        log.info("Searching wines where wine quality is " + wineQuality+ "..");
        return Optional.ofNullable(wineQuality)
                .map(wq -> wineRepository.findByWineQuality(WineQuality.valueOf(wq))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<WineDTO> findByAvailableAndWineryName(final boolean available, final String wineryName){
        log.info("Searching wines where availability is "+available+" and winery name is " + wineryName+ "..");
        return Optional.ofNullable(wineryName)
                .map(wn -> wineRepository.findByAvailableAndWinery_Name(available, wn)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<WineDTO> findByAvailableAndWineQuality(final boolean available, final String wineQuality){
        log.info("Searching wines where availability is "+available+" and wine quality is " + wineQuality+ "..");
        return Optional.ofNullable(wineQuality)
                .map(wq -> wineRepository.findByAvailableAndWineQuality(available, WineQuality.valueOf(wq))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }
}
