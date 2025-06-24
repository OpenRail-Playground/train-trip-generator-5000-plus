package ch.hack4rail.traintripgenerator.repositories;

import ch.hack4rail.traintripgenerator.entities.StopTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.hack4rail.traintripgenerator.entities.StopEntity;
import ch.hack4rail.traintripgenerator.entities.TripEntity;

@Repository
public interface StopTimeRepository extends JpaRepository<StopTimeEntity, String> {
}
