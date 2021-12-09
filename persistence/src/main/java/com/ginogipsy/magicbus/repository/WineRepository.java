package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.enums.WineQuality;
import com.ginogipsy.magicbus.domain.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WineRepository extends JpaRepository<Wine, Integer> {

    Wine findByName(String name);
    List<Wine> findByWinery_Name(String wineryName);
    List<Wine> findByWineQuality(WineQuality wineQuality);
    List<Wine> findByAvailable(Boolean available);
    List<Wine> findByAvailableAndWinery_Name(boolean available, String wineryName);
    List<Wine> findByAvailableAndWineQuality(boolean available, WineQuality wineQuality);
}
