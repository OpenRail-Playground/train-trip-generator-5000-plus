package ch.hack4rail.traintipgenerator.response;

import java.util.List;

public record TripResponse(
        List<TripResponsePart> trips
) {
}
