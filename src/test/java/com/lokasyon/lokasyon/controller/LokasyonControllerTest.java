package com.lokasyon.lokasyon.controller;

import com.lokasyon.lokasyon.advice.ControllerAdvice;
import com.lokasyon.lokasyon.dto.CityDto;
import com.lokasyon.lokasyon.dto.DistrictDto;
import com.lokasyon.lokasyon.exception.NotFoundException;
import com.lokasyon.lokasyon.service.LokasyonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LokasyonControllerTest {
    @Mock
    LokasyonServiceImpl lokasyonService;
    @InjectMocks
    LokasyonController lokasyonController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(lokasyonController)
                .setControllerAdvice(new ControllerAdvice())
                .build();
    }

    @Test
    void getCities() throws Exception {
        CityDto cityDto= new CityDto();
        List<CityDto> expected = Arrays.asList(cityDto);
        when(lokasyonService.getAllCitys()).thenReturn(expected);
        mockMvc.perform(get("/api/lokasyon/cities"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllDistricts()throws Exception  {
        List<DistrictDto> districtDtoList = new ArrayList<>();
        districtDtoList.add(new DistrictDto("ADANA"));

        Mockito.when(lokasyonService.getDistrictsByCity(anyString())).thenReturn(districtDtoList);

        mockMvc.perform(get("/api/lokasyon/ankara/districts").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void getAllDistricts_shouldReturnBadRequest() throws Exception {

        Mockito.when(lokasyonService.getDistrictsByCity(anyString())).thenThrow(new NotFoundException("İçel şehrinde kayıtlı ilçe bulunamadı"));
        mockMvc.perform(get("/api/lokasyon/içel/districts").
                        contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());



    }}