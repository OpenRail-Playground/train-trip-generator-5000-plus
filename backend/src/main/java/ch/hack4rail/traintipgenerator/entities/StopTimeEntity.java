package ch.hack4rail.traintipgenerator.entities;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String tripId;

    @Column
    private String departureTime;

    @Column
    private String arrivalTime;
}
