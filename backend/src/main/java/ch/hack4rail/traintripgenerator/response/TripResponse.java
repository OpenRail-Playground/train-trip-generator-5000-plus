package ch.hack4rail.traintripgenerator.response;

import java.util.List;

public record TripResponse(
        List<TripResponsePart> trips
) {
}
