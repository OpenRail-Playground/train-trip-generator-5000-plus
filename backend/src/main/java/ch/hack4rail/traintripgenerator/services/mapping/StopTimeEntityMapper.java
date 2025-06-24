package ch.hack4rail.traintripgenerator.services.mapping;


import ch.hack4rail.traintripgenerator.entities.StopTimeEntity;
import ch.hack4rail.traintripgenerator.entities.StopTimeId;
import ch.hack4rail.traintripgenerator.gtfs.StopTime;

import java.time.LocalTime;

public class StopTimeEntityMapper {


    public static StopTimeEntity map(StopTime e){

        return StopTimeEntity.builder()
                .id(StopTimeId.builder()
                        .tripId(e.getTripId())
                        .stopSequence(e.getStopSequence())
                        .build())
                .stopId(e.getStopId())
                .departureTime(LocalTime.parse(e.getDepartureTime()))
                .arrivalTime(LocalTime.parse(e.getArrivalTime()))
                .build();
    }
}
