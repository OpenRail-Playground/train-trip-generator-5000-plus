package ch.hack4rail.traintripgenerator.controller;

import ch.hack4rail.traintripgenerator.request.AutocompletionRequest;
import ch.hack4rail.traintripgenerator.response.AutocompletionResponse;
import ch.hack4rail.traintripgenerator.response.AutocompletionResponsePart;
import ch.hack4rail.traintripgenerator.services.AutocompletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stations")
public class StationsController {

    private final AutocompletionService autocompletionService;

    @PostMapping("/autocomplete")
    public AutocompletionResponse getAutocompletion(@RequestBody AutocompletionRequest request) {

        final var foundStops = autocompletionService.searchDatabaseForStops(request.stationName());

        return new AutocompletionResponse(
                foundStops.stream().map(stop -> new AutocompletionResponsePart(stop.getId(), stop.getName()))
                        .collect(Collectors.toList()));
    }
}
