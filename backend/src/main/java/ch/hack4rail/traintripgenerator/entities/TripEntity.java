package ch.hack4rail.traintripgenerator.entities;

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
public final class TripEntity {

    @Id
    private String id;
    @Column
    private String routeId;
    @Column
    private String serviceId;
    @Column
    private String tripShortName;
    @Column
    private String directionId;
}
