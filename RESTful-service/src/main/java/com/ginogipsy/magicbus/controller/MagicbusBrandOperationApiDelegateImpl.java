package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.controller.model.InsertBrandRequest;
import com.ginogipsy.magicbus.controller.model.MagicBusResult;
import com.ginogipsy.magicbus.dto.BrandDTO;
import com.ginogipsy.magicbus.service.BrandService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author ginogipsy
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class MagicbusBrandOperationApiDelegateImpl implements MagicbusBrandOperationApiDelegate{

    private final BrandService brandService;

    @Override
    public ResponseEntity<MagicBusResult> insertBrand(InsertBrandRequest insertBrandRequest) {
        final BrandDTO brandToInsert = new BrandDTO();
        brandToInsert.setName(insertBrandRequest.getBrandName());
        brandToInsert.setDescription(insertBrandRequest.getDescription());
        brandService.insert(brandToInsert);
        return ResponseUtils.buildSuccessResult("Brand named "+insertBrandRequest.getBrandName()+" has been inserted correctly!");
    }
}
