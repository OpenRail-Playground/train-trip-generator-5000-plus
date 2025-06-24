package main.java.ch.hack4rail.traintipgenerator.controller;

import main.java.ch.hack4rail.traintipgenerator.request.StopRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StopController {

    @PostMapping("/stop")
    public String getStop(@RequestBody StopRequest request) {
        return "Hallo, " + request.stationName() + "!";
    }

}
