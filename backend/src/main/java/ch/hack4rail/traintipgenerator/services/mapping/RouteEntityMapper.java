package ch.hack4rail.traintipgenerator.services.mapping;

import ch.hack4rail.traintipgenerator.entities.RouteEntity;
import ch.hack4rail.traintipgenerator.gtfs.Route;

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
