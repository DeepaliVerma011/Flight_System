package com.example.flight_status.controller;

import com.example.flight_status.repository.FlightRepository;
import com.example.flight_status.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public String showFlightStatus(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String showAddFlightPage() {
        return "add-flight";
    }

    @PostMapping("/add")
    public String addFlight(@RequestParam("number") String number,
                            @RequestParam("status") String status,
                            @RequestParam("gate") String gate,
                            @RequestParam("arrivalTime") String arrivalTime,
                            @RequestParam("departureTime") String departureTime) {
        Flight flight = new Flight();
        flight.setNumber(number);
        flight.setStatus(status);
        flight.setGate(gate);
        flight.setArrivalTime(arrivalTime);
        flight.setDepartureTime(departureTime);
        flightRepository.save(flight);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteFlight(@RequestParam("id") Long id) {
        flightRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateFlight(@RequestParam("id") Long id,
                               @RequestParam("number") String number,
                               @RequestParam("status") String status,
                               @RequestParam("gate") String gate,
                               @RequestParam("arrivalTime") String arrivalTime,
                               @RequestParam("departureTime") String departureTime) {
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight != null) {
            flight.setNumber(number);
            flight.setStatus(status);
            flight.setGate(gate);
            flight.setArrivalTime(arrivalTime);
            flight.setDepartureTime(departureTime);
            flightRepository.save(flight);
        }
        return "redirect:/";
    }
    @GetMapping("/update-flight")
    public String showUpdateFlightPage(@RequestParam("id") Long id, Model model) {
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight == null) {
            // Handle case when flight is not found
            return "redirect:/";
        }
        model.addAttribute("flight", flight);
        return "update-flight";
    }

}
