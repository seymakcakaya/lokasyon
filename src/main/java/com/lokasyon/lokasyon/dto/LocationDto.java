package com.lokasyon.lokasyon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LocationDto {
    String city;
    String district;
    String town;
    String neighborhood;
    String zipCode;

    public LocationDto(String city, String district, String town, String zipCode) {
        this.city=city;
        this.district=district;
        this.town=town;
        this.zipCode=zipCode;

    }
}
