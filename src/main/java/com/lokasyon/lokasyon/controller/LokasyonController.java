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
    @Operation(summary = "Şehirler", description = "Bu method  veritabanındaki tüm şehirleri listeler")
    public ResponseEntity<List<CityDto>> getCities() {
        return ResponseEntity.ok(lokasyonService.getAllCitys());
    }

    @GetMapping("/{city}/districts")
    @Operation(summary = "İlçe Bilgileri", description = "Bu method belirli şehire ait tüm ilçeleri listeler")
    public ResponseEntity<List<DistrictDto>> getAllDistricts(@PathVariable("city") String city) {
        List<DistrictDto> districList=lokasyonService.getDistrictsByCity(city);
        return  new ResponseEntity<>(districList, HttpStatus.OK);
    }

    @GetMapping("/neighborhoods")
    @Operation(summary = "Mahalle Bilgileri", description = "Bu method şehir ve semt bilgilerine göre mahalleri listeler")
    public ResponseEntity<List<NeighborhoodDto> >getAllNeighborhood(@RequestParam(value = "city") String city, @RequestParam(value = "town") String town) {
        return ResponseEntity.ok(lokasyonService.getNeighborhoodsByTownAndCity(city, town));
    }
    @GetMapping("/locations")
    @Operation(summary = "Detaylı Lokasyon Bilgisi", description = "Bu method posta kodunun bağlı oladuğu tüm lokasyonları gösterir.")
    public ResponseEntity<List<LocationDto>> findLocationsByZipCode(@RequestParam(value = "code") String code ) {

            List<LocationDto>locationDtoList =  lokasyonService.findLocationsByZipCode(code);
            return new ResponseEntity<>(locationDtoList,HttpStatus.OK);

    }
    @GetMapping("/{city}/locations")
    @Operation(summary = "Şehir Lokasyon Bilgileri", description = "Bu method  belirli şehirdeki tüm lokasyonları listeler")
    public ResponseEntity<List<LocationDto>> getAllLocationsByCity(@PathVariable("city") String city) {
        return ResponseEntity.ok(lokasyonService.getAllLocationsByCity(city));
    }
    @GetMapping("/district/{zipcode}")
    @Operation(summary = "Lokasyon Bilgisi", description = "Bu method girilen posta kodunun bağlı olguğu  il,ilçe ve semt bilgilerini gösterir.")
    public ResponseEntity<LocationDto> getLocationInfoByZipCode(@PathVariable("zipcode") String zipCode) {
        return ResponseEntity.ok(lokasyonService.getLocationInfoByZipCode(zipCode));
    }

}
