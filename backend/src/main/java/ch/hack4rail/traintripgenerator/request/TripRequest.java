package ch.hack4rail.traintripgenerator.request;

import java.time.LocalTime;

public record TripRequest(
        Long departureId,
        Long destinationId,
        LocalTime travelDayStartTime,
        Integer maxTravelTimePerDayInHours
) {
}
