package ch.hack4rail.traintripgenerator.services.mapping;

import ch.hack4rail.traintripgenerator.entities.TripEntity;
import ch.hack4rail.traintripgenerator.gtfs.Trip;
import ch.hack4rail.traintripgenerator.repositories.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripEntityMapper {

    @Autowired
    private RouteRepository routeRepository;

    public TripEntity map(Trip e){
        val foundRoute = routeRepository.findById(e.getRouteId()).orElseThrow();

        return TripEntity.builder()
                .id(e.getId())
                .route(foundRoute)
                .serviceId(e.getServiceId())
                .tripShortName(e.getTripShortName())
                .directionId(e.getDirectionId())
                .build();
    }
}
