package com.lokasyon.lokasyon;

import com.lokasyon.lokasyon.controller.LokasyonController;
import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.DistrictDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.exception.NotFoundException;
import com.lokasyon.lokasyon.service.LokasyonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class LokasyonControllerTest {
    @Mock
    LokasyonService lokasyonService;
    @InjectMocks
    LokasyonController lokasyonController;

    @Test
    public void listofCity() {
        //given
        CityDto cityDto = new CityDto();
        List<CityDto> expected = Arrays.asList(cityDto);

        when(lokasyonService.getAllCitys()).thenReturn(expected);
        //when
        ResponseEntity<List<CityDto>> response = lokasyonController.getCities();
        List<CityDto> actual = response.getBody();
        //then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expected.size(), actual.size())
        );

    }

    @Test
    public void getAllDistricts() {
        String city = "ADANA";
        List<DistrictDto> districtDtoList = getExpectedDistrictDtoList();

        when(lokasyonService.getDistrictsByCity(anyString())).thenReturn(districtDtoList);
        ResponseEntity<List<DistrictDto>> response = lokasyonController.getAllDistricts(city);
        List<DistrictDto> actual = response.getBody();
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(districtDtoList.size(), actual.size())
        );
    }
    @Test
    public void getLocationByZipCode_shouldReturnStatusNotFound() {
        //given
        when(lokasyonService.getLocationByZipCode(anyString())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> lokasyonController.getLocationByZipCode("1"));

    }

    @Test//şehir ismi ile ilçeler listelenmeli
    public void getAllDistricts_shouldReturnBadRequest() {
        String city = "İçel";
        when(lokasyonService.getDistrictsByCity(anyString())).thenReturn(null);
        ResponseEntity<List<DistrictDto>> response = lokasyonController.getAllDistricts(city);
        List<DistrictDto> actual = response.getBody();
        assertAll(
                () -> assertNull(actual),
                () -> assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode())
        );

    }

    private List<DistrictDto> getExpectedDistrictDtoList() {
        List<DistrictDto> districtDtoList = new ArrayList<>();
        List<String> districtList = Arrays.asList("SEYHAN", "YÜREĞİR", "FEKE", "POZANTI", "KARAİSALI", "KARATAŞ", "ÇUKUROVA", "İMAMOĞLU", "SARIÇAM", "KOZAN", "CEYHAN", "SAİMBEYLİ", "ALADAĞ", "YUMURTALIK", "TUFANBEYLİ");
        for (String district : districtList) {
            districtDtoList.add(new DistrictDto(district));
        }
        return districtDtoList;
    }
}
