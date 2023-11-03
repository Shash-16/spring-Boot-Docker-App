package com.bth.carFamily.entity;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private String id;
    private String carBrand;
    private String parentCompany;
    private int yearOfOrigin;
    private String country;
}
