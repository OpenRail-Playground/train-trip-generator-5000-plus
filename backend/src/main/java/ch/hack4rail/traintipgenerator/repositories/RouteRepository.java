package ch.hack4rail.traintipgenerator.repositories;

import ch.hack4rail.traintipgenerator.entities.StopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<StopEntity, String> {
}
