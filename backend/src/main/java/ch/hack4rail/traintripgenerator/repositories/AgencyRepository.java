package ch.hack4rail.traintripgenerator.repositories;

import ch.hack4rail.traintripgenerator.entities.AgencyEntity;
import ch.hack4rail.traintripgenerator.entities.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<AgencyEntity, Long> {
}
