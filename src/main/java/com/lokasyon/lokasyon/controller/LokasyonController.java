package com.lokasyon.lokasyon.controller;


import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.DistrictDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.dto.NeighborhoodDto;
import com.lokasyon.lokasyon.service.LokasyonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lokasyon")

public class LokasyonController {

    private final LokasyonService lokasyonService;

    @Autowired
    public LokasyonController(LokasyonService lokasyonService) {
        this.lokasyonService = lokasyonService;
    }

    @GetMapping("/cities")
    @Cacheable("cities")
    @Operation(summary = "List Of City", description = "Bu method  veritabanındaki tüm şehirleri listeler")
    public ResponseEntity<List<CityDto>> getCities() {
        return ResponseEntity.ok(lokasyonService.getAllCitys());
    }

    @GetMapping("/{city}/districts")
    @Operation(summary = "List Of district", description = "Bu method belirli şehire ait tüm ilçeleri listeler")
    public ResponseEntity<List<DistrictDto>> getAllDistricts(@PathVariable("city") String city) {
        List<DistrictDto> districList=lokasyonService.getDistrictsByCity(city);
        return  new ResponseEntity<>(districList, HttpStatus.OK);
    }

    @GetMapping("/neighborhoods")
    @Operation(summary = "List Of neighborhood", description = "Bu method ilçelere ait mahalleri listeler")
    public ResponseEntity<List<NeighborhoodDto> >getAlleighborhood(@RequestParam(value = "city") String city, @RequestParam(value = "town") String town) {
        return ResponseEntity.ok(lokasyonService.getNeighborhoodsByTown(city, town));
    }
    @GetMapping("/locations")
    @Operation(summary = "List Of Location", description = "Bu method posta konuna göre tüm lokasyonları  listeler")
    public ResponseEntity<List<LocationDto>> getLocationByZipCode(@RequestParam(value = "code") String code ) {

            List<LocationDto>locationDtoList =  lokasyonService.getLocationByZipCode(code);
            return new ResponseEntity<>(locationDtoList,HttpStatus.OK);

    }
    @GetMapping("/{city}/locations")
    @Operation(summary = "Lokasyon Bilgileri", description = "Bu method  belirli şehirdeki tüm lokasyonları listeler")
    public ResponseEntity<List<LocationDto>> getLocations(@PathVariable("city") String city) {
        return ResponseEntity.ok(lokasyonService.getLocations(city));
    }
    @GetMapping("/district/{zipcode}")
    @Operation(summary = "ilçe Bilgileri", description = "Bu method girilen zip koda ait ilçe ve şehir ve semt  bilgilerini gösterir.")
    public ResponseEntity<LocationDto> getDistictByZipCode(@PathVariable("zipcode") String zipCode) {
        return ResponseEntity.ok(lokasyonService.getCityAndDistrictAndTownByZipCode(zipCode));
    }

}
