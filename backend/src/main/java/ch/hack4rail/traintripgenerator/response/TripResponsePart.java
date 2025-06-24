package ch.hack4rail.traintripgenerator.response;

import java.time.LocalDateTime;

public record TripResponsePart(
        float departureStationLatitude,
        float departureStationLongitude,
        float arrivalStationLatitude,
        float arrivalStationLongitude,
        String departureStationName,
        String arrivalStationName,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        String trainName
) {
}
