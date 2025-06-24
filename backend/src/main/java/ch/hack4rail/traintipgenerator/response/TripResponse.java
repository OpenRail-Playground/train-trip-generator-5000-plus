package ch.hack4rail.traintipgenerator.response;

import java.util.List;
import ch.hack4rail.traintripgenerator.gtfs.Trip;

public record TripResponse(
        List<Trip> trips
) {
}
