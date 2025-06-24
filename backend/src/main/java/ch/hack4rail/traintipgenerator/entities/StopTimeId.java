package ch.hack4rail.traintipgenerator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class StopTimeId implements Serializable {
    private String tripId;

    private String stopSequence;

}