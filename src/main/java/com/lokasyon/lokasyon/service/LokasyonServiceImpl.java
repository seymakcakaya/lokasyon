package com.lokasyon.lokasyon.service;

import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.DistrictDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.dto.NeighborhoodDto;
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
        List<DistrictDto> districtDtos = repository.getDistrictsByCity(city.toUpperCase());
        if (districtDtos.isEmpty()) {
            throw new NotFoundException("arama sonucunda ilçe bulunamdı");
        }
        return districtDtos;
    }

    @Override
    public List<NeighborhoodDto> getNeighborhoodsByTown(String city, String town) {
//        if (districtDtos.isEmpty()){
//            throw new NotFoundException("arama sonucunda ilçe bulunamdı");
//        }
        return repository.getNeighborhoodsByTown(city.toUpperCase(), town.toUpperCase());
    }

    @Override
    public List<LocationDto> getLocationByZipCode(String zipcode) {
        //        if (districtDtos.isEmpty()){
//            throw new NotFoundException("arama sonucunda ilçe bulunamdı");
//        }
        return repository.findLocationByZipCode(zipcode);

    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationDto> getLocations(String city) {
        List<LocationDto> locationDtoList = repository.getAllLocation(city.toUpperCase());
        if (locationDtoList.isEmpty()) {
           throw  new NotFoundException("Girilen %s şehre   ait lokasyon bulunamadı", city);
        }
        return locationDtoList;
    }

    @Override
    public LocationDto getCityAndDistrictAndTownByZipCode(String zipCode) {
        LocationDto locationDto = repository.getCityAndDistrictByZipCode(zipCode)
                .orElseThrow(() -> new NotFoundException("Girilen %s numaraları zip koduna ait lokasyon bulunamadı", zipCode));
        return locationDto;
    }
}
