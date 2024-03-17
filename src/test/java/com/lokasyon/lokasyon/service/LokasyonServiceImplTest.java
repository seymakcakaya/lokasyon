package com.lokasyon.lokasyon.service;

import com.lokasyon.lokasyon.dto.CityDto;
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
        when(lokasyonRepository.getAllLocation(anyString())).thenReturn(Collections.EMPTY_LIST);
        // when then
        assertThrows(NotFoundException.class, () ->lokasyonService.getLocations(city));

    }

    @Test
    void getNeighborhoodsByTown() {
    }

    @Test
    void getLocationByZipCode() {
    }

    @Test
    void getLocations() {
    }

    @Test
    void getCityAndDistrictAndTownByZipCode() {
    }
}