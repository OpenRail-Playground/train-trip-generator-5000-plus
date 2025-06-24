package ch.hack4rail.traintipgenerator.repositories;

import ch.hack4rail.traintipgenerator.entities.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, String> {
}
