package ch.hack4rail.traintripgenerator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.hack4rail.traintripgenerator.entities.TripEntity;

@Repository
public interface TripRepository extends JpaRepository<TripEntity, Long> {
}
