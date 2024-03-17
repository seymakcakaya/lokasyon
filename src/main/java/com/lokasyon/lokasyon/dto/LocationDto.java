package com.lokasyon.lokasyon.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
@Builder
@Data
public class LocationDto  {
    String city;
    String district;
    String town;
    String neighborhood;
    String zipCode;
}