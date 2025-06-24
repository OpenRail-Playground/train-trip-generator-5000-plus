package ch.hack4rail.traintripgenerator.entities;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "stop_times")
public final class StopTimeEntity {

    @EmbeddedId
    private StopTimeId id;

    @ManyToOne(targetEntity = StopEntity.class)
    private StopEntity stop;

    @Column
    private LocalTime departureTime;

    @Column
    private LocalTime arrivalTime;
}
