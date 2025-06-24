package ch.hack4rail.traintripgenerator.controller;

import java.time.Duration;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.hack4rail.traintripgenerator.request.AutocompletionRequest;
import ch.hack4rail.traintripgenerator.request.TripRequest;
import ch.hack4rail.traintripgenerator.response.AutocompletionResponse;
import ch.hack4rail.traintripgenerator.response.AutocompletionResponsePart;
import ch.hack4rail.traintripgenerator.response.TripResponse;
import ch.hack4rail.traintripgenerator.services.AutocompletionService;
import ch.hack4rail.traintripgenerator.services.GraphSearchService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TrainTripGeneratorController {

	private final AutocompletionService autocompletionService;
	private final GraphSearchService graphSearchService;

	@PostMapping("/autocomplete")
	public AutocompletionResponse getAutocompletion(@RequestBody AutocompletionRequest request) {

		final var foundStops = autocompletionService.searchDatabaseForStops(request.stationName());

		return new AutocompletionResponse(
				foundStops.stream().map(stop -> new AutocompletionResponsePart(stop.getId(), stop.getName()))
						.collect(Collectors.toList()));
	}

	@PostMapping("/trip")
    public TripResponse getTrip(@RequestBody TripRequest request) {
    	Optional<TripResponse> response = graphSearchService.getOptimalRoute(request.departureId(), 
    			request.destinationId(), 
    			Duration.ofHours(request.maxTravelTimePerDayInHours()), 
    			request.travelDayStartTime(), Duration.ofMinutes(6));
        return response.orElseThrow();
    }

}
