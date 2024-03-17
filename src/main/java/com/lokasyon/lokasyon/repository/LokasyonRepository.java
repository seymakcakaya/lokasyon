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

    @Query("select distinct (city) from lokasyon order by city asc")
    List<CityDto> getAllCity();


    @Query("select distinct (district) from lokasyon  where city = :p_city  ")
    List<DistrictDto> getDistrictsByCity(@Param("p_city") String city);

    @Query("select distinct (neighborhood) from lokasyon  where city=:city and town=:town order by neighborhood ")
  List<NeighborhoodDto> getNeighborhoodsByTownAndCity(@Param("city") String city, @Param("town") String town);

    @Query("select city,district,town,neighborhood,zip_code from Lokasyon where zip_code =:code")
   List<LocationDto> findLocationsByZipCode(@Param("code") String zipcode);

    @Query("select city,district,town,neighborhood,zip_code from lokasyon where city =:pCity")
    List<LocationDto> getAllLocationsByCity(@Param("pCity") String city);

    @Query("select  city,district,town, zip_code from lokasyon  where zip_code=:code group by city,district,town, zip_code ;")
    Optional<LocationDto> getLocationInfoByZipCode(@Param("code") String zipcode);
}
