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
import java.util.Optional;

@Repository
public interface LokasyonRepository extends CrudRepository<Lokasyon, Long> {

    @Query("select distinct (city) from Lokasyon order by city asc")
    List<CityDto> getAllCity();


    @Query("select distinct (district) from Lokasyon  where city = :p_city  ")
    List<DistrictDto> getDistrictsByCity(@Param("p_city") String city);

    @Query("select distinct (neighborhood) from Lokasyon  where city=:city and town=:town order by neighborhood ")
  List<NeighborhoodDto> getNeighborhoodsByTown(@Param("city") String city, @Param("town") String town);

    @Query("select city,district,town,neighborhood,zip_code from Lokasyon where zip_code =:code")
   List<LocationDto> findLocationByZipCode(@Param("code") String zipcode);

    @Query("select city,district,town,neighborhood,zip_code from lokasyon where city =:pCity")
    List<LocationDto> getAllLocation(@Param("pCity") String city);

    @Query("select  city,district,town, zip_code from lokasyon  where zip_code=:code group by city,district,town, zip_code ;")
    Optional<LocationDto> getCityAndDistrictByZipCode(@Param("code") String zipcode);
}
