package main.java.ch.hack4rail.traintipgenerator.request;

public record TripRequest(
        String departureStation,
        String destinationStation,
        String travelDayStartTime,
        String travelTimePerDay
) {
}
