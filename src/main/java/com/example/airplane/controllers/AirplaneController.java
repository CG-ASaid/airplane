package com.example.airplane.controllers;

import com.example.airplane.exceptions.NotFoundException;
import com.example.airplane.models.Airplane;
import com.example.airplane.models.Location;
import com.example.airplane.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//TODO: Logic in services package if enough time

@RestController
@RequestMapping("/api/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneRepository airplaneRepository;

    public AirplaneController() {
    }

    //Create a new airplane, starting location Amsterdam
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Airplane createAirplane(@RequestBody Airplane airplane) {
        airplane.setFuel(5);
        airplane.setLocation(Location.AMSTERDAM);
        this.airplaneRepository.save(airplane);
        return airplane;
    }

    //Get a list of airplanes
    @RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
    public Iterable<Airplane> getAll() {
        return this.airplaneRepository.findAll();
    }

    @RequestMapping(value = "/fly/{id}/{location}", method = RequestMethod.PUT)
    public Airplane fly(@PathVariable Long id, @PathVariable String location) {
        Optional<Airplane> oAirplane = this.airplaneRepository.findById(id);

        if (oAirplane.isPresent()) {
            Airplane airplane = oAirplane.get();

            //Only fly if there's enough fuel
            if (airplane.getFuel() >= 2) {
                airplane.setFuel(airplane.getFuel() - 2);
                airplane.setLocation(Location.valueOf(location));
                this.airplaneRepository.save(airplane);
                return airplane;
            }
        }

        throw new NotFoundException();
    }

    @RequestMapping(value = "/refuel/{id}", method = RequestMethod.PUT)
    public Airplane refuel(@PathVariable Long id) {
        Optional<Airplane> oAirplane = this.airplaneRepository.findById(id);

        if (oAirplane.isPresent()) {
            Airplane airplane = oAirplane.get();
            airplane.setFuel(5);
            this.airplaneRepository.save(airplane);
            return airplane;
        }

        throw new NotFoundException();
    }
}
