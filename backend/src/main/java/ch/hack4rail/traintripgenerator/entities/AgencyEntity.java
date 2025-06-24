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
@Entity(name = "agencies")
public final class AgencyEntity {

    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String agencyUrl;

    @Column
    private String agencyTimezone;

    @Column
    private String agencyLang;

}
