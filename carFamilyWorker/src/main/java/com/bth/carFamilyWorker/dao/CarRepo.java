package com.bth.carFamilyWorker.dao;

import com.bth.carFamilyWorker.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepo extends MongoRepository<Car, String> {
    List<Car> findByCarBrand(String name);

    List<Car> findByParentCompany(String name);

    List<Car> findByCountry(String country);
}
