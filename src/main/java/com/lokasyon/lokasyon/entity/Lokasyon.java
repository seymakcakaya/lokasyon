package com.lokasyon.lokasyon.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "lokasyon")
public class Lokasyon {
    @Id
    private Long id;
    private String city;

    private String district;
    private String town;
    private String neighborhood;
    @Column(value = "zip_code")
    private String zipCode;

}
