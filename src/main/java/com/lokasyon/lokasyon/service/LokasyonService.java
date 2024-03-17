package com.lokasyon.lokasyon.service;

import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.DistrictDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.dto.NeighborhoodDto;

import java.util.List;

public interface LokasyonService {

    List<CityDto> getAllCitys();

    List<DistrictDto> getDistrictsByCity(String city);

    List<NeighborhoodDto> getNeighborhoodsByTown(String city, String town);

    List<LocationDto> getLocationByZipCode(String zipcode);

    List<LocationDto> getLocations(String city);

    LocationDto getCityAndDistrictAndTownByZipCode(String zipCode);


}
