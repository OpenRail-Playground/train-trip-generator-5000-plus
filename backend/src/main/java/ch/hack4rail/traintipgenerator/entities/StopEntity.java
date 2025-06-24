package ch.hack4rail.traintipgenerator.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StopEntity {

    @Id
    private String id;

    private String name;

    private String parentStationId;

    private String latitude;

    private String longitude;

}
