package com.lokasyon.lokasyon.service;

import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.DistrictDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.dto.NeighborhoodDto;
import com.lokasyon.lokasyon.exception.NeighborhoodNotFoundException;
import com.lokasyon.lokasyon.repository.LokasyonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//Loglama eklenebilir AOP kullanarak
//Exception yapısı ekle dedi

@Service
public class LokasyonService implements ILokasyonService {

    private LokasyonRepository repository;
    @Autowired
    public LokasyonService(LokasyonRepository repository){
        this.repository=repository;
    }


    @Override
    public List<CityDto> getAllCitys() {
        return repository.getAllCity();
    }

    @Override
    public List<DistrictDto> getDistrictsByCity(String city) {
        return repository.getDistrictsByCity(city);
    }

    @Override
    public List<NeighborhoodDto> getNeighborhoodsByTown(String city,String town) {
        List<NeighborhoodDto> neighborhoodList=repository.getNeighborhoodsByTown(city,town);
        if (neighborhoodList.isEmpty()){
            throw new NeighborhoodNotFoundException("Arama sonucunda mahalle bulunamadı");
        }
        return neighborhoodList;
    }

    @Override
    public List<LocationDto> getLocationByZipCode(String zipcode) {
        return repository.findLocationByZipCode(zipcode);
    }

    @Override
    public List<LocationDto> getLocations(String city) {
        return repository.getAllLocation(city);
    }
}
