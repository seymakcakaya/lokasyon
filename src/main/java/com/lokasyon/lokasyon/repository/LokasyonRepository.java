package com.lokasyon.lokasyon.repository;

import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.DistrictDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.dto.NeighborhoodDto;
import com.lokasyon.lokasyon.entity.Lokasyon;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LokasyonRepository extends CrudRepository<Lokasyon,Long> {

    @Query("select distinct (city) from Lokasyon ")
    public List<CityDto> getAllCity();

    @Query("select distinct (district) from Lokasyon  where city = :p_city  ")

    public List<DistrictDto> getDistrictsByCity(@Param("p_city") String city);

    @Query("select distinct (neighborhood) from Lokasyon  where city=:city and town=:town")
    public List<NeighborhoodDto> getNeighborhoodsByTown(@Param("city")String city,@Param("town")String town);


    @Query("select city,district,town,neighborhood,zip_code from Lokasyon zip_code is null or zip_code =: code")
    List<LocationDto> findLocationByZipCode(@Param("code") String zipcode);

    @Query("select city,district,town,neighborhood,zip_code from lokasyon")
    List<LocationDto> getAllLocation();
}
