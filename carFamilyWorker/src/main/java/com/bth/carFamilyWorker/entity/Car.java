package com.bth.carFamilyWorker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Car")
public record Car(@Id String id, String carBrand, String parentCompany, int yearOfOrigin, String country) {
}