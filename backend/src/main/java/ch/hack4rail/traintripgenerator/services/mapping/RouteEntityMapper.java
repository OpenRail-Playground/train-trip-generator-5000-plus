package ch.hack4rail.traintripgenerator.services.mapping;

import ch.hack4rail.traintripgenerator.entities.RouteEntity;
import ch.hack4rail.traintripgenerator.gtfs.Route;

public class RouteEntityMapper {


    public static RouteEntity map(Route e){
        return RouteEntity.builder()
                .id(e.getId())
                .agencyId(e.getAgencyId())
                .routeType(e.getRouteType())
                .routeShortName(e.getRouteShortName())
                .routeLongName(e.getRouteLongName())
                .build();
    }
}
