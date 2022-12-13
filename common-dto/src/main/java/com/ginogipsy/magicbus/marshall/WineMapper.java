package com.ginogipsy.magicbus.marshall;


import com.ginogipsy.magicbus.domain.Wine;
import com.ginogipsy.magicbus.domain.enums.WineQualityEnum;
import com.ginogipsy.magicbus.dto.WineDTO;
import com.ginogipsy.magicbus.repository.WineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class WineMapper {

    private final ModelMapper modelMapper;
    private final WineRepository wineRepository;

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

    public Optional<Wine> convertToEntity(final Optional<WineDTO> wineDTO) {
        return Optional.ofNullable(wineDTO)
                .map(w -> modelMapper.map(w, Wine.class));
    }

    public Optional<WineDTO> convertToDTO(final Optional<Wine> wine) {
        return Optional.ofNullable(wine)
                .map(w -> modelMapper.map(w, WineDTO.class));
    }

    public Optional<WineDTO> findByName(final String name){
        log.info("WineMapper - findByName() -> Searching wine where name is '{}' ..", name);
        return Optional.ofNullable(name)
                .map(wineRepository::findByName)
                .map(this::convertToDTO);
    }

    public List<WineDTO> findByWineryName(final String wineryName){
        log.info("WineMapper - findByWineryName() -> Searching wines where winery name is {}..", wineryName);
        return Optional.ofNullable(wineryName)
                .map(w -> wineRepository.findByWinery_Name(w)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<WineDTO> findByAvailable(final boolean available){
        log.info("WineMapper - findByAvailable() -> Searching wines where available is {}..", available);
        return wineRepository.findByAvailable(available)
                .stream()
                .map(this::convertToDTO)
                .toList();
        }

    public List<WineDTO> findByWineQuality(final String wineQuality){
        log.info("WineMapper - findByWineQuality() -> Searching wines where wine quality is {}..", wineQuality);
        return Optional.ofNullable(wineQuality)
                .map(wq -> wineRepository.findByWineQualityEnum(WineQualityEnum.valueOf(wq))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<WineDTO> findByAvailableAndWineryName(final boolean available, final String wineryName){
        log.info("WineMapper - findByAvailableAndWineryName() -> Searching wines where availability is {} and winery name is {}..", available, wineryName);
        return Optional.ofNullable(wineryName)
                .map(wn -> wineRepository.findByAvailableAndWinery_Name(available, wn)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<WineDTO> findByAvailableAndWineQuality(final boolean available, final String wineQuality){
        log.info("WineMapper - findByAvailableAndWineQuality() -> Searching wines where availability is {} and winery name is {}..", available, wineQuality);
        return Optional.ofNullable(wineQuality)
                .map(wq -> wineRepository.findByAvailableAndWineQualityEnum(available, WineQualityEnum.valueOf(wq))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }
}
