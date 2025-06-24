package ch.hack4rail.traintipgenerator.services.mapping;

import ch.hack4rail.traintipgenerator.entities.StopEntity;
import ch.hack4rail.traintipgenerator.gtfs.Stop;

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
