package com.lokasyon.lokasyon.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public record LocationDto (String city, String district, String town, String neighborhood, String zipCode) {
}