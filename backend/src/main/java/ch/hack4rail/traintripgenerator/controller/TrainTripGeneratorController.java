package ch.hack4rail.traintripgenerator.controller;

import ch.hack4rail.traintipgenerator.response.AutocompletionResponse;
import ch.hack4rail.traintipgenerator.response.AutocompletionResponsePart;
import ch.hack4rail.traintipgenerator.response.TripResponse;
import ch.hack4rail.traintipgenerator.response.TripResponsePart;
import ch.hack4rail.traintripgenerator.request.AutocompletionRequest;
import ch.hack4rail.traintripgenerator.request.TripRequest;
import ch.hack4rail.traintripgenerator.services.AutocompletionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TrainTripGeneratorController {

    private final AutocompletionService autocompletionService;

    public TrainTripGeneratorController(AutocompletionService autocompletionService) {
        this.autocompletionService = autocompletionService;
    }

    @PostMapping("/autocomplete")
    public AutocompletionResponse getAutocompletion(@RequestBody AutocompletionRequest request) {

        final var foundStops = autocompletionService.searchDatabaseForStops(request.stationName());

        return new AutocompletionResponse(
                foundStops.stream().map(
                        stop -> new AutocompletionResponsePart(Long.parseLong(stop.getId()), stop.getName())
                ).collect(Collectors.toList())
        );
    }

    @PostMapping("/trip")
    public TripResponse getTrip(@RequestBody TripRequest request) {

        return new TripResponse(
                List.of(new TripResponsePart(123L, "abc"))
        );
    }

}
