package com.lokasyon.lokasyon.service;

import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.DistrictDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.dto.NeighborhoodDto;

import java.util.List;

public interface ILokasyonService {

    public List<CityDto> getAllCitys();
    public List<DistrictDto> getDistrictsByCity(String city);
    public List<NeighborhoodDto> getNeighborhoodsByTown(String city,String town);
    public List<LocationDto> getLocationByZipCode(String zipcode);
    public List<LocationDto> getLocations();


}
