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
@Entity(name = "routes")
public final class RouteEntity {

    @Id
    private String id;

    @Column
    private String agencyId;

    @Column
    private String routeShortName;

    @Column
    private String routeLongName;

    @Column
    private String routeDesc;

    @Column
    private String routeType;


}
