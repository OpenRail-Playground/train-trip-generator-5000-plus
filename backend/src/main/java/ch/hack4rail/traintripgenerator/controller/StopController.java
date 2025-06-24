package ch.hack4rail.traintripgenerator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.hack4rail.traintripgenerator.request.StopRequest;

@RestController
@RequestMapping("/api")
public class StopController {

    @PostMapping("/stop")
    public String getStop(@RequestBody StopRequest request) {
        return "Hallo, " + request.stationName() + "!";
    }

}
