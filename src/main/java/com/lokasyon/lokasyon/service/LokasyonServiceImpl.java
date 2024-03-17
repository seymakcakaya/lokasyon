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
        List<DistrictDto> districtDtos = repository.getDistrictsByCity(city.toUpperCase());
        if (districtDtos.isEmpty()) {
            throw new NotFoundException("%s şehirinde kayıtlı ilçe bulunamadı", city);
        }
        return districtDtos;
    }

    @Override
    public List<NeighborhoodDto> getNeighborhoodsByTownAndCity(String city, String town) {
        List<NeighborhoodDto> neighborhoodDtoList = repository.getNeighborhoodsByTownAndCity(city.toUpperCase(), town.toUpperCase());
        if (neighborhoodDtoList.isEmpty()) {
            throw new NeighborhoodNotFoundException("%s şehri %s semtine ait mahalle bulunamadı", city, town);
        }

        return neighborhoodDtoList;
    }

    @Override
    public List<LocationDto> findLocationsByZipCode(String zipcode) {
        List<LocationDto> locationDtoList = repository.findLocationsByZipCode(zipcode);
                if (locationDtoList.isEmpty()){
            throw new NotFoundException("Girilen %s numaraları zip koduna ait lokasyon bulunamadı", zipcode);
        }
        return locationDtoList;

    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationDto> getAllLocationsByCity(String city) {
        List<LocationDto> locationDtoList = repository.getAllLocationsByCity(city.toUpperCase());
        if (locationDtoList.isEmpty()) {
           throw  new NotFoundException("Girilen %s şehre   ait lokasyon bulunamadı", city);
        }
        return locationDtoList;
    }

    @Override
    public LocationDto getLocationInfoByZipCode(String zipCode) {
        LocationDto locationDto = repository.getLocationInfoByZipCode(zipCode)
                .orElseThrow(() -> new NotFoundException("Girilen %s numaraları zip koduna ait lokasyon bulunamadı", zipCode));
        return locationDto;
    }
}
