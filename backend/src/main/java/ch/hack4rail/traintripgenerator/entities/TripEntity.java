package ch.hack4rail.traintripgenerator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
    private long id;

    @ManyToOne(targetEntity = RouteEntity.class)
    @JoinColumn(name = "route_id")
    private RouteEntity route;

    @Column
    private long serviceId;

    @Column
    private String tripShortName;

    @Column
    private long directionId;
}
