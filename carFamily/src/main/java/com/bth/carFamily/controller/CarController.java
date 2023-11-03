package com.bth.carFamily.controller;

import com.bth.carFamily.entity.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class CarController {

    RestTemplate restTemplate = new RestTemplate();
    @Value("${WORKER_URI:http://localhost:9001/api}")
    private String API_URL;

    @GetMapping("/index")
    public String welcomePage() {
        return "index";
    }

    @GetMapping("/search")
    public String searchPage(Car car) {
        return "search";
    }

    @PostMapping("/update/")
    public String addCar(Car car,
                         RedirectAttributes redirectAttributes, BindingResult result, Model model) {
        HttpEntity<Car> request = new HttpEntity<>(car);
        ResponseEntity<Car> response = restTemplate
                .exchange(API_URL + "/cars", HttpMethod.POST, request, Car.class);
        redirectAttributes.addFlashAttribute("successMessage", String.format("Details Added Successfully for car brand: %s", car.getCarBrand()));
        return "redirect:/index";
    }

    @PostMapping("/update/{id}")
    public String updateCar(@PathVariable("id") String id, Car car,
                            RedirectAttributes redirectAttributes, Model model) {
        HttpEntity<Car> request = new HttpEntity<>(car);
        ResponseEntity<Car> response = restTemplate
                .exchange(API_URL + "/update", HttpMethod.PUT, request, Car.class);
        redirectAttributes.addFlashAttribute("successMessage", String.format("Details Updated Successfully for car brand: %s", car.getCarBrand()));
        return "redirect:/index";
    }

    @GetMapping("/cars")
    public String showAllCars(Model model) {
        ResponseEntity<Car[]> response = restTemplate.getForEntity(API_URL + "/cars", Car[].class);
        List<Car> cars = new ArrayList<>(List.of(Objects.requireNonNull(response.getBody())));
        if (cars.isEmpty()) {
            model.addAttribute("cars", null);
        } else {
            model.addAttribute("cars", cars);
        }
        return "cars-detail";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Car car = restTemplate.getForObject(API_URL + "/cars/edit/" + id, Car.class);
        model.addAttribute("car", car);
        return "update-car";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") String id,
                            RedirectAttributes redirectAttributes, Model model) {
        restTemplate.delete(API_URL + "/delete/" + id);
        redirectAttributes.addFlashAttribute("successMessage", "Car is deleted!");
        return "redirect:/index";
    }

    @PostMapping("/search")
    public String searchCar(Car car, Model model) {
        ResponseEntity<Car[]> response = restTemplate.getForEntity(API_URL + "/cars/" + car.getCarBrand(), Car[].class);
        List<Car> cars = new ArrayList<>(List.of(Objects.requireNonNull(response.getBody())));
        if (cars.isEmpty()) {
            model.addAttribute("cars", null);
        } else {
            model.addAttribute("cars", cars);
        }
        return "cars-detail";
    }

    @GetMapping("/addCar")
    public String addCar(Car car) {
        return "update-car";
    }

}
