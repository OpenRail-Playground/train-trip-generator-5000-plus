package ch.hack4rail.traintipgenerator.entities;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Entity
public class StopTimeEntity {

    @EmbeddedId
    private StopTimeId id;

    @Column
    private String stopId;

    @Column
    private LocalTime departureTime;

    @Column
    private LocalTime arrivalTime;
}
