package ch.hack4rail.traintipgenerator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity(name = "trips")
public class TripEntity {

    @Column
    private String routeId;
    @Id
    private String tripId;
    @Column
    private String serviceId;
    @Column
    private String tripShortName;
    @Column
    private String directionId;
}
