package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.Beer;
import com.ginogipsy.magicbus.domain.enums.BeerTypeEnum;
import com.ginogipsy.magicbus.dto.BeerDTO;
import com.ginogipsy.magicbus.repository.BeerRepository;
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
public class BeerMapper {

    private final ModelMapper modelMapper;
    private final BeerRepository beerRepository;

    public Beer convertToEntity(final BeerDTO beerDTO) {
        return Optional.ofNullable(beerDTO)
                .map(b -> modelMapper.map(b, Beer.class))
                .orElse(null);
    }

    public BeerDTO convertToDTO(final Beer beer) {
        return Optional.ofNullable(beer)
                .map(b -> modelMapper.map(b, BeerDTO.class))
                .orElse(null);
    }

    public Optional<Beer> convertToEntity(final Optional<BeerDTO> beerDTO) {
        return Optional.ofNullable(beerDTO)
                .map(b -> modelMapper.map(b, Beer.class));
    }

    public Optional<BeerDTO> convertToDTO(final Optional<Beer> beer) {
        return Optional.ofNullable(beer)
                .map(b -> modelMapper.map(b, BeerDTO.class));
    }

    public Optional<BeerDTO> findByName(final String name) {
        log.info("BeerMapper - findByName() -> Searching beer where name is '{}' ..", name);
        return Optional.ofNullable(name)
                .flatMap(beerRepository::findByName)
                .map(this::convertToDTO);
    }

    public List<BeerDTO> findByBreweryName(final String breweryName) {
        log.info("BeerMapper - findByName() -> Searching beers where breweryName is '{}' ..", breweryName);
        return Optional.ofNullable(breweryName)
                .map(bn -> beerRepository.findByBrewery_Name(bn)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<BeerDTO> findByBeerType(final String beerType) {
        log.info("BeerMapper - findByBeerType() -> Searching beers where beer type is '{}'..", beerType);
        return Optional.ofNullable(beerType)
                .map(bt -> beerRepository.findByBeerTypeEnum(BeerTypeEnum.valueOf(bt))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());

    }
    public List<BeerDTO> findByAvailable(final boolean available) {
        log.info("BeerMapper - findByAvailable() -> Searching beers where available is '{}'..", available);
        return beerRepository.findByAvailable(available)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<BeerDTO> findByAvailableAndBrewery_Name(final boolean available, final String breweryName) {
        log.info("BeerMapper - findByAvailableAndBrewery_Name() -> Searching beers where available is '{}' and brewery name is '{}'..", available, breweryName);
        return Optional.ofNullable(breweryName)
                .map(bw -> beerRepository.findByAvailableAndBrewery_Name(available, bw)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public List<BeerDTO> findByAvailableAndBeerType(final boolean available, final String beerType) {
        log.info("BeerMapper - findByAvailableAndBeerType() -> Searching beers where available is '{}' and beer type is '{}'..", available, beerType);
        return Optional.ofNullable(beerType)
                .map(bt -> beerRepository.findByAvailableAndBeerTypeEnum(available, BeerTypeEnum.valueOf(bt))
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }
}
