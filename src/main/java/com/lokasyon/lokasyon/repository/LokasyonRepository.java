package com.lokasyon.lokasyon.repository;

import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.DistrictDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.dto.NeighborhoodDto;
import com.lokasyon.lokasyon.entity.Lokasyon;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LokasyonRepository extends CrudRepository<Lokasyon, Long> {

    @Query("select new com.lokasyon.lokasyon.dto.CityDto (city) from Lokasyon group by city order by city asc")
    List<CityDto> getAllCity();


    @Query("select new com.lokasyon.lokasyon.dto.DistrictDto( district) from Lokasyon  where city = :p_city group by district ")
    List<DistrictDto> getDistrictsByCity(@Param("p_city") String city);

    @Query("select new com.lokasyon.lokasyon.dto.NeighborhoodDto( neighborhood )from Lokasyon  where city=:city and town=:town  group by neighborhood order by neighborhood  ")
  List<NeighborhoodDto> getNeighborhoodsByTown(@Param("city") String city, @Param("town") String town);

    @Query("select new com.lokasyon.lokasyon.dto.LocationDto(city,district,town,neighborhood,zipCode) from Lokasyon where zipCode =:code")
   List<LocationDto> findLocationByZipCode(@Param("code") String zipcode);

    @Query("select new com.lokasyon.lokasyon.dto.LocationDto( city,district,town,neighborhood,zipCode ) from Lokasyon where city =:pCity")
    List<LocationDto> getAllLocation(@Param("pCity") String city);

    @Query("select new com.lokasyon.lokasyon.dto.LocationDto( city,district,town,zipCode) from Lokasyon  where zipCode=:code group by city,district,town, zipCode")
    Optional<LocationDto> getCityAndDistrictByZipCode(@Param("code") String zipcode);
}
