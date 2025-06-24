package ch.hack4rail.traintripgenerator.services.mapping;

import ch.hack4rail.traintripgenerator.entities.TripEntity;
import ch.hack4rail.traintripgenerator.gtfs.Trip;

public class TripEntityMapper {

    public static TripEntity map(Trip e){
        return TripEntity.builder()
                .id(e.getId())
                .routeId(e.getRouteId())
                .serviceId(e.getServiceId())
                .tripShortName(e.getTripShortName())
                .directionId(e.getDirectionId())
                .build();
    }
}
