package com.lokasyon.lokasyon.service;

import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.exception.NotFoundException;
import com.lokasyon.lokasyon.repository.LokasyonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LokasyonServiceImplTest {

    @Mock
    LokasyonRepository lokasyonRepository;
    @InjectMocks//under test
    LokasyonServiceImpl lokasyonService;


    @Test
    void getAllCitys() {
        //given
        CityDto cityDto = new CityDto();
        cityDto.setCity("ADANA");
        List<CityDto> cityDtoList = Collections.singletonList(cityDto);
        when(lokasyonRepository.getAllCity()).thenReturn(cityDtoList);
        //when
        List<CityDto> actual = lokasyonService.getAllCitys();
        //Then
        assertEquals(cityDto.getCity(), actual.get(0).getCity());


    }

    @Test
    void getDistrictsByCity_shoulThrowExcepiton() {
        //given
        String city = "İçel";
        when(lokasyonRepository.getAllLocationsByCity(anyString())).thenReturn(Collections.EMPTY_LIST);
        // when then
        assertThrows(NotFoundException.class, () -> lokasyonService.getAllLocationsByCity(city));

    }

    @Test
    void getNeighborhoodsByTownAndCity_shouldThrowException() {
        String city = "istanbul";
        String town = "Kayaşehir";
        when(lokasyonRepository.getNeighborhoodsByTownAndCity(anyString(), anyString())).thenReturn(Collections.EMPTY_LIST);
        assertThrows(NotFoundException.class, () -> lokasyonService.getNeighborhoodsByTownAndCity(city, town));
    }

    @Test
    void getLocationByZipCode() {
        String zipCode = "1";
        when(lokasyonRepository.getLocationInfoByZipCode(anyString())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> lokasyonService.findLocationsByZipCode(zipCode));
    }


    @Test
    void getCityAndDistrictAndTownByZipCode() {
        LocationDto locationDto = LocationDto.builder().city("ADANA").district("CEYHAN").town("BÜYÜKMANGIT").zipCode("1922").build();

        when(lokasyonRepository.getLocationInfoByZipCode(anyString())).thenReturn(Optional.of(locationDto));

        LocationDto actual = lokasyonService.getLocationInfoByZipCode("1922");
        assertEquals(locationDto.getCity(), actual.getCity());
        assertEquals(locationDto.getTown(), actual.getTown());
        assertEquals(locationDto.getDistrict(), actual.getDistrict());
    }
}