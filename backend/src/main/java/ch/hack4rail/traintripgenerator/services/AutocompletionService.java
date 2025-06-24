package ch.hack4rail.traintripgenerator.services;

import ch.hack4rail.traintripgenerator.entities.StopEntity;
import ch.hack4rail.traintripgenerator.repositories.StopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutocompletionService {

    private final StopRepository stopRepository;

    public AutocompletionService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public List<StopEntity> searchDatabaseForStops(String stationName) {
        return stopRepository.search(stationName);
    }


}
