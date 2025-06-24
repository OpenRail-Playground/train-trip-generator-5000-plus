package main.java.ch.hack4rail.traintipgenerator.controller;

import main.java.ch.hack4rail.traintipgenerator.request.AutocompleteRequest;
import main.java.ch.hack4rail.traintipgenerator.request.TripRequest;
import main.java.ch.hack4rail.traintipgenerator.response.TrainTrip;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StopController {

    @PostMapping("/autocomplete")
    public String getAutocomplete(@RequestBody AutocompleteRequest request) {
        return "Hallo, " + request.stationName() + "!";
    }

    @PostMapping("/trip")
    public List<TrainTrip> getTrip(@RequestBody TripRequest request) {
        return new ArrayList<>();
    }


}
