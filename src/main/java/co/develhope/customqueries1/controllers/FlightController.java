package co.develhope.customqueries1.controllers;

import co.develhope.customqueries1.entities.Flight;
import co.develhope.customqueries1.entities.FlightStatus;
import co.develhope.customqueries1.repositories.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    List<Flight> flights = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> createFlights() {
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            Flight flight = new Flight();
            flight.setDescription(Integer.toString(rand.nextInt()));
            flight.setFromAirport(Integer.toString(rand.nextInt()));
            flight.setToAirport(Integer.toString(rand.nextInt()));
            flight.setStatus(FlightStatus.ON_TIME);
            flights.add(flight);
        }
        flightRepository.saveAll(flights);
        return ResponseEntity.status(HttpStatus.CREATED).body("50 flights have been created");
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getFlights() {
        List<Flight> flights = flightRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(flights);
    }

    //@GetMapping
    //public List<Flight> getFlights(){
    //    flights = flightRepository.findAll();
    //    return flights;
    //}

}