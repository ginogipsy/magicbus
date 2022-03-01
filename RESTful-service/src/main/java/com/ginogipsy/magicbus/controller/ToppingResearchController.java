package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.service.ToppingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/researchTopping")
public class ToppingResearchController {

    private final ToppingService toppingService;

    public ToppingResearchController(ToppingService toppingService) {
        this.toppingService = toppingService;
    }

    @GetMapping(value = "/findByName", params = "tasteName")
    @ApiOperation(value = "Get topping", notes = "Get topping by name")
    public ResponseEntity<ToppingDTO> findByName(@RequestParam String tasteName) {
        return ResponseEntity.ok().body(toppingService.findByName(tasteName));
    }

    @GetMapping(value = "/findInName")
    @ApiOperation(value = "Get topping", notes = "Get topping where name contains")
    public ResponseEntity<List<ToppingDTO>> findInName(@RequestParam String nameContains){
        return ResponseEntity.ok(toppingService.findTasteWhereNamesContains(nameContains));
    }

    @GetMapping(value = "/findByBase")
    @ApiOperation(value = "Get topping", notes = "Get topping by bae")
    public ResponseEntity<List<ToppingDTO>> findByBase(@RequestParam String base){
        return ResponseEntity.ok(toppingService.findByBase(base));
    }

    @GetMapping(value = "/findByStatus")
    @ApiOperation(value = "Get topping", notes = "Get topping by statu")
    public ResponseEntity<List<ToppingDTO>> findByStatus(@RequestParam(defaultValue = "DISPONIBILE") String status){
        return ResponseEntity.ok(toppingService.findByStatus(status));
    }

    @GetMapping(value = "/findByProductCategory")
    @ApiOperation(value = "Get topping", notes = "Get topping by product category")
    public ResponseEntity<List<ToppingDTO>> findByProductCategory(@RequestParam String productCategory){
        return ResponseEntity.ok(toppingService.findByProductCategory(productCategory));
    }

    @GetMapping(value = "/findByAvailabilityPeriod")
    @ApiOperation(value = "Get topping", notes = "Get topping by availability period")
    public ResponseEntity<List<ToppingDTO>> findByAvailabilityPeriod(@RequestParam String availabilityPeriod){
        return ResponseEntity.ok(toppingService.findByAvailabilityPeriod(availabilityPeriod));
    }

    @GetMapping(value = "/findByAvailabilityAndStatus")
    @ApiOperation(value = "Get topping", notes = "Get topping by availability and status")
    public ResponseEntity<List<ToppingDTO>> findByAvailabilityAndStatus(@RequestParam(defaultValue = "true") boolean available, @RequestParam String status){
        return ResponseEntity.ok(toppingService.findByAvailabilityAndStatus(available, status));
    }

    @GetMapping(value = "/findByAvailableAndAvailabilityPeriod")
    @ApiOperation(value = "Get topping", notes = "Get topping by availability and availability period")
    public ResponseEntity<List<ToppingDTO>> findByAvailableAndAvailabilityPeriod(@RequestParam(defaultValue = "true") boolean available, @RequestParam String availabilityPeriod){
        return ResponseEntity.ok(toppingService.findByAvailableAndAvailabilityPeriod(available, availabilityPeriod));
    }

    @GetMapping(value = "/findByUserEntered")
    @ApiOperation(value = "Get topping", notes = "Get user entered topping")
    public ResponseEntity<List<ToppingDTO>> findByUserEntered(@RequestParam(defaultValue = "true") boolean available){
        return ResponseEntity.ok(toppingService.findByUserEntered(available));
    }

    @GetMapping(value = "/findByUserEnteredAndStatus")
    @ApiOperation(value = "Get topping", notes = "Get user entered topping by status")
    public ResponseEntity<List<ToppingDTO>> findByUserEnteredAndStatus(@RequestParam(defaultValue = "true") boolean available, @RequestParam String status){
        return ResponseEntity.ok(toppingService.findByUserEnteredAndStatus(available, status));
    }
}
