package com.bth.carFamilyWorker.controller;

import com.bth.carFamilyWorker.dao.CarRepo;
import com.bth.carFamilyWorker.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarRepo carRepo;

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        return carRepo.save(car);
    }

    @GetMapping("/cars")
    public List<Car> findAllCars() {
        return carRepo.findAll();
    }

    @GetMapping("/cars/{name}")
    public List<Car> findCar(@PathVariable String name) {
        return carRepo.findByCarBrand(name).isEmpty() ? carRepo.findByParentCompany(name) : carRepo.findByCarBrand(name);
    }

    @GetMapping("/cars/edit/{id}")
    public Optional<Car> findCarById(@PathVariable String id) {
        return carRepo.findById(id);
    }

    @GetMapping("/country/{country}")
    public List<Car> findCarsByCountry(@PathVariable String country) {
        return carRepo.findByCountry(country);
    }

    @PutMapping("/update")
    public Car updateCar(@RequestBody Car car) {
        return carRepo.save(car);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCar(@PathVariable String id) {
        carRepo.deleteById(id);
    }


}
