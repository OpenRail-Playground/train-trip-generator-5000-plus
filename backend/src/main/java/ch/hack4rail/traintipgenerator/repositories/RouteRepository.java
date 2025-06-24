package ch.hack4rail.traintipgenerator.repositories;

import ch.hack4rail.traintipgenerator.entities.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, String> {
}
