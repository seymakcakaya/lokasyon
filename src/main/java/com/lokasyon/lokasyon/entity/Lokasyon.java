package com.lokasyon.lokasyon.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Data
@ToString
@Table(name = "lokasyon")
@Entity
public class Lokasyon {

    @Id
    private Long id;
    private String city;

    private String district;
    private String town;
    private String neighborhood;
    @Column(name = "zip_code")
    private String zipCode;

}
