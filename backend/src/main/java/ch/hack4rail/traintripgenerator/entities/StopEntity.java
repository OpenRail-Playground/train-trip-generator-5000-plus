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
@Entity(name = "stops")
public class StopEntity {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String parentStationId;

    @Column
    private String latitude;

    @Column
    private String longitude;

}
