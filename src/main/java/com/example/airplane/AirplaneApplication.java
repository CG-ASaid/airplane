package com.example.airplane;

import com.example.airplane.models.Airplane;
import com.example.airplane.models.Location;
import com.example.airplane.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class AirplaneApplication {

	private final AirplaneRepository airplaneRepository;

	@Autowired
	public AirplaneApplication(AirplaneRepository airplaneRepository) {
		this.airplaneRepository = airplaneRepository;
	}

	//Fill database with first airplane (free of charge!)
	@EventListener(ApplicationReadyEvent.class)
	public void onStart() {
		Airplane airplane = new Airplane("Boeing 727");
		airplane.setLocation(Location.AMSTERDAM);
		this.airplaneRepository.save(airplane);
	}

	public static void main(String[] args) {
		SpringApplication.run(AirplaneApplication.class, args);
	}

}
