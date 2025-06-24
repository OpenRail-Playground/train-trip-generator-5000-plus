package ch.hack4rail.traintripgenerator.controller;

import java.time.Duration;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.hack4rail.traintripgenerator.request.TripRequest;
import ch.hack4rail.traintripgenerator.response.TripResponse;
import ch.hack4rail.traintripgenerator.services.GraphSearchService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/routes")
public class RoutesController {

 	private final GraphSearchService graphSearchService;

	@PostMapping("/trip")
    public TripResponse getTrip(@RequestBody TripRequest request) {
    	Optional<TripResponse> response = graphSearchService.getOptimalRoute(request.departureId(), 
    			request.destinationId(), 
    			Duration.ofHours(request.maxTravelTimePerDayInHours()), 
    			request.travelDayStartTime(), Duration.ofMinutes(6));
        return response.orElseThrow();
    }

}
