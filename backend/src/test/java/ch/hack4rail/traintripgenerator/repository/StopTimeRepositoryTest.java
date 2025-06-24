package ch.hack4rail.traintripgenerator.repository;

import ch.hack4rail.traintripgenerator.entities.StopTimeEntity;
import ch.hack4rail.traintripgenerator.entities.StopTimeId;
import ch.hack4rail.traintripgenerator.repositories.StopTimeRepository;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalTime;
import java.util.List;

@DataJpaTest
public class StopTimeRepositoryTest {

    @Autowired
    private StopTimeRepository stopTimeRepository;

    @Test
    public void testFindByTripIdOrdered() {

        val tripId = 2L;
        val stopTimes = List.of(
                StopTimeEntity.builder()
                        .id(StopTimeId.builder()
                                .tripId(tripId)
                                .stopSequence(3)
                                .build())
                        .departureTime(LocalTime.now())
                        .arrivalTime(LocalTime.now())
                        .build(),
                StopTimeEntity.builder()
                        .id(StopTimeId.builder()
                                .tripId(tripId)
                                .stopSequence(1)
                                .build())
                        .departureTime(LocalTime.now())
                        .arrivalTime(LocalTime.now())
                        .build(),
                StopTimeEntity.builder()
                        .id(StopTimeId.builder()
                                .tripId(tripId)
                                .stopSequence(2)
                                .build())
                        .departureTime(LocalTime.now())
                        .arrivalTime(LocalTime.now())
                        .build()

        );

        stopTimeRepository.saveAll(stopTimes);

        val found = stopTimeRepository.findByTripIdOrdered(tripId);
        Assertions.assertNotNull(found);
        Assertions.assertEquals(1, found.get(0).getId().getStopSequence());
        Assertions.assertEquals(2, found.get(1).getId().getStopSequence());
        Assertions.assertEquals(3, found.get(2).getId().getStopSequence());

    }


}
