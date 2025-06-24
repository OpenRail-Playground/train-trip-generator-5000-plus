package ch.hack4rail.traintripgenerator.services.mapping;

import ch.hack4rail.traintripgenerator.entities.AgencyEntity;
import ch.hack4rail.traintripgenerator.gtfs.Agency;

public class AgencyEntityMapper {


    public static AgencyEntity map(Agency e){
        return AgencyEntity.builder()
                .id(e.getId())
                .name(e.getName())
                .agencyTimezone(e.getTimezone())
                .agencyUrl(e.getUrl())
                .agencyLang(e.getLanguage())
                .build();
    }
}
