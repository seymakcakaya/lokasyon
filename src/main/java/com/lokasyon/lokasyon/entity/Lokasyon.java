package com.lokasyon.lokasyon.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "lokasyon")
public class Lokasyon {
    @Id
    @Column(value = "id")
    private Long id;
    @Column(value = "city")
    private String city;
    @Column(value = "district")

    private String district;
    @Column(value = "town")
    private String town;
    @Column(value = "neighborhood")
    private String neighborhood;
    @Column(value = "zip_code")
    private String zipCode;

}
