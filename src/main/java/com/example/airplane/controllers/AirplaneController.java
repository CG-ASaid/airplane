package com.example.airplane.controllers;

import com.example.airplane.models.Airplane;
import com.example.airplane.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//TODO: Logic in services package if enough time

@RestController
@RequestMapping("/api/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneRepository airplaneRepository;

    public AirplaneController() {
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Airplane createAirplane(Airplane airplane) {
        airplane.setFuel(5);
        this.airplaneRepository.save(airplane);
        return airplane;
    }

    @RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
    public List<Airplane> getAll() {
        List<Airplane> result = new ArrayList<Airplane>();
        Iterable<Airplane> retrieved = this.airplaneRepository.findAll();
        retrieved.forEach(result::add);
        return result;
    }
}
