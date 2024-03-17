package com.lokasyon.lokasyon;

import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.LocationDto;
import com.lokasyon.lokasyon.dto.NeighborhoodDto;
import com.lokasyon.lokasyon.exception.NeighborhoodNotFoundException;
import com.lokasyon.lokasyon.exception.NotFoundException;
import com.lokasyon.lokasyon.repository.LokasyonRepository;
import com.lokasyon.lokasyon.service.LokasyonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LokasyonServiceTest {
    @Mock
    private LokasyonRepository lokasyonRepository;

    @InjectMocks
    private LokasyonService lokasyonService;

    @Test
    public void getLocationByZipCode() {
        //"ADANA"	"CEYHAN"	"BÜYÜKMANGIT"	"1922"
        LocationDto locationDto = new LocationDto("ADANA", "CEYHAN", "BÜYÜKMANGIT", "", "1922");
        when(lokasyonRepository.getCityAndDistrictByZipCode(anyString())).thenReturn(Optional.of(locationDto));
        LocationDto actual = lokasyonService.getCityAndDistrictAndTownByZipCode("1922");
        assertEquals(locationDto.city(), actual.city());
        assertEquals(locationDto.town(), actual.town());
        assertEquals(locationDto.district(), actual.district());

    }

    @Test
    public void getAllDistrictsByCity_shouldReturnBadRequest() {
        String city = "İçel";
        when(lokasyonRepository.getAllLocation(anyString())).thenReturn(Optional.empty());
        Executable executable = () -> lokasyonService.getLocations(city);
        assertThrows(NotFoundException.class, executable)
    }

    @Test
    public void getAllCitys_itShouldReturnCitDtoList() {
        CityDto cityDto = new CityDto();
        cityDto.setCity("ADANA");
        List<CityDto> cityDtoList = Collections.singletonList(cityDto);
        when(lokasyonRepository.getAllCity()).thenReturn(cityDtoList);

        List<CityDto> actual = lokasyonService.getAllCitys();
        assertEquals(cityDto.getCity(), actual.get(0).getCity());
    }

    @Test
    public void getNeighborhoodsByTown_shouldThrowException() {

        String city = "istanbul";
        String town = "kayaşehir";
        when(lokasyonRepository.getNeighborhoodsByTown(anyString(), anyString())).thenReturn(Optional.empty());
        assertThrows(NeighborhoodNotFoundException.class, () -> lokasyonService.getNeighborhoodsByTown(city, town));

    }

    @Test
    public void getNeighborhoodsByTown() {
        String city = "İSTANBUL";
        String town = "BÜYÜKADA";
        NeighborhoodDto n1 = new NeighborhoodDto("MADEN MAH");
        NeighborhoodDto n2 = new NeighborhoodDto("NİZAM MAH");
        List<NeighborhoodDto> neighborhoodDtos = Arrays.asList(n1, n2);
        when(lokasyonRepository.getNeighborhoodsByTown(anyString(), anyString())).thenReturn(Optional.of(neighborhoodDtos));
        List<NeighborhoodDto> actual = lokasyonService.getNeighborhoodsByTown(city, town);
        assertNotNull(actual);
        assertEquals(neighborhoodDtos.size(), actual.size());
        assertEquals(neighborhoodDtos.get(0), actual.get(0));
        assertEquals(neighborhoodDtos.get(1), actual.get(1));

    }

}
