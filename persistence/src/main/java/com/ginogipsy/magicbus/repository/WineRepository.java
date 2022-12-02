package com.ginogipsy.magicbus.repository;

import com.ginogipsy.magicbus.domain.enums.WineQuality;
import com.ginogipsy.magicbus.domain.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author ginogipsy
 */
public interface WineRepository extends JpaRepository<Wine, Integer> {

    Wine findByName(final String name);
    List<Wine> findByWinery_Name(final String wineryName);
    List<Wine> findByWineQuality(final WineQuality wineQuality);
    List<Wine> findByAvailable(final Boolean available);
    List<Wine> findByAvailableAndWinery_Name(final boolean available, final String wineryName);
    List<Wine> findByAvailableAndWineQuality(final boolean available, final WineQuality wineQuality);
}
