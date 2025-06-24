package ch.hack4rail.traintripgenerator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@Builder
public final class StopTimeId implements Serializable {

    @Column
    private Long tripId;

    @Column
    private Integer stopSequence;

}