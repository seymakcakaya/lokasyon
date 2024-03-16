package com.lokasyon.lokasyon.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public record Location(@Id Integer id,String city,String district,String town,String neighborhood,@Column(value = "zip_code")String zipCode) {
}
