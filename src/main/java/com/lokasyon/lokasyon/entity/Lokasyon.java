package com.lokasyon.lokasyon.entity;


import jakarta.persistence.*;
import lombok.*;


@Data
@ToString
@Table(name = "lokasyon")
@Entity
public class Lokasyon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String district;
    private String town;
    private String neighborhood;
    @Column(name = "zip_code")
    private String zipCode;

}
