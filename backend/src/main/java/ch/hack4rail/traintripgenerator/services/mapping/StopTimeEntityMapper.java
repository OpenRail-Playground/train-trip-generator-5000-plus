package ch.hack4rail.traintripgenerator.services.mapping;


import ch.hack4rail.traintripgenerator.entities.StopTimeEntity;
import ch.hack4rail.traintripgenerator.entities.StopTimeId;
import ch.hack4rail.traintripgenerator.gtfs.StopTime;
import ch.hack4rail.traintripgenerator.repositories.StopRepository;
import ch.hack4rail.traintripgenerator.repositories.StopTimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class StopTimeEntityMapper {

    private final StopRepository stopRepository;

    public StopTimeEntity map(StopTime e) {
        val found = stopRepository.findById(e.getStopId()).orElseThrow();
        return StopTimeEntity.builder()
                .id(StopTimeId.builder()
                        .tripId(e.getTripId())
                        .stopSequence(e.getStopSequence())
                        .build())
                .stop(found)
                .departureTime(LocalTime.parse(e.getDepartureTime()))
                .arrivalTime(LocalTime.parse(e.getArrivalTime()))
                .build();
    }
}
