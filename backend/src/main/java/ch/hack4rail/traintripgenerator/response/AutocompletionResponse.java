package ch.hack4rail.traintripgenerator.response;

import java.util.List;

public record AutocompletionResponse(
        List<AutocompletionResponsePart> stationRecommendations
) {
}
