package com.lokasyon.lokasyon.controller;


import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.DistrictDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.dto.NeighborhoodDto;
import com.lokasyon.lokasyon.service.ILokasyonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lokasyon")

public class LokasyonController {

    private ILokasyonService lokasyonService;

    @Autowired
    public LokasyonController(ILokasyonService lokasyonService) {
        this.lokasyonService = lokasyonService;
    }

    @GetMapping("/cities")
    @Cacheable("cities")
    @Operation(summary = "List Of City", description = "Bu method  veritabanındaki tüm şehirleri listeler")
    public ResponseEntity<List<CityDto>> getCities() {
        return ResponseEntity.ok(lokasyonService.getAllCitys());
    }

    @GetMapping("/{city}/district")
    @Operation(summary = "List Of district", description = "Bu method belirli şehire ait tüm ilçeleri listeler")
    public List<DistrictDto> getAllDistricts(@PathVariable("city") String city) {
        return lokasyonService.getDistrictsByCity(city);
    }

    @GetMapping("/neighborhood")
    @Operation(summary = "List Of neighborhood", description = "Bu method ilçelere ait mahalleri listeler")
    public List<NeighborhoodDto> getAllneighborhood(@RequestParam(value = "city", required = true) String city, @RequestParam(value = "district", required = true) String district) {
        return lokasyonService.getNeighborhoodsByTown(city, district);
    }

    @GetMapping("/zipcode")
    @Operation(summary = "List Of neighborhood", description = "Bu method ilçelere ait mahalleri listeler")
    public List<LocationDto> getAllneighborhood(@RequestParam(value = "code", required = false) String code ) {
        return lokasyonService.getLocationByZipCode(code);
    }
    @GetMapping("/locations")

    @Operation(summary = "Lokasyon Bilgileri", description = "Bu method  belirli şehirdeki tüm lokasyonları listeler")
    public ResponseEntity<List<LocationDto>> getLocations(@RequestParam String city) {
        return ResponseEntity.ok(lokasyonService.getLocations(city));
    }


}
