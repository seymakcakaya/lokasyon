package com.lokasyon.lokasyon.service;

import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.DistrictDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.dto.NeighborhoodDto;
import com.lokasyon.lokasyon.exception.NeighborhoodNotFoundException;
import com.lokasyon.lokasyon.exception.NotFoundException;
import com.lokasyon.lokasyon.repository.LokasyonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//Loglama eklenebilir AOP kullanarak
//Exception yapısı ekle dedi

@Service
public class LokasyonServiceImpl implements LokasyonService {

    private LokasyonRepository repository;

    @Autowired
    public LokasyonServiceImpl(LokasyonRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<CityDto> getAllCitys() {
        return repository.getAllCity();
    }

    @Override
    public List<DistrictDto> getDistrictsByCity(String city) {

        return repository.getDistrictsByCity(city.toUpperCase()).get();
    }

    @Override
    public List<NeighborhoodDto> getNeighborhoodsByTown(String city, String town) {
        List<NeighborhoodDto> neighborhoodList = repository.getNeighborhoodsByTown(city.toUpperCase(), town.toUpperCase())
                .orElseThrow(() -> new NeighborhoodNotFoundException("Arama sonucunda mahalle bulunamadı"));
        return neighborhoodList;
    }

    @Override
    public List<LocationDto> getLocationByZipCode(String zipcode) {
        List<LocationDto> locationList = repository.findLocationByZipCode(zipcode)
                .orElseThrow(() -> new NotFoundException("Girilen %s numaraları zip koduna ait lokasyon bulunamadı", zipcode));
        return locationList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationDto> getLocations(String city) {
        List<LocationDto> locationList = repository.getAllLocation(city.toUpperCase())
                .orElseThrow(() -> new NotFoundException("Girilen %s şehrine ait lokasyon bulunamadı", city.toUpperCase()));
        return locationList;
    }

    @Override
    public LocationDto getCityAndDistrictAndTownByZipCode(String zipCode) {
        LocationDto locationDto = repository.getCityAndDistrictByZipCode(zipCode)
                .orElseThrow(() -> new NotFoundException("Girilen %s numaraları zip koduna ait lokasyon bulunamadı", zipCode));
        return locationDto;
    }
}
