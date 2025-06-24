package ch.hack4rail.traintripgenerator.services.mapping;

import ch.hack4rail.traintripgenerator.entities.StopEntity;
import ch.hack4rail.traintripgenerator.gtfs.Stop;

public class StopEntityMapper {


    public static StopEntity map(Stop e){
        return StopEntity.builder()
                .id(e.getId())
                .name(e.getName())
                .longitude(e.getLongitude())
                .latitude(e.getLatitude())
                .build();
    }
}
