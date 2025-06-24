package ch.hack4rail.traintripgenerator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.hack4rail.traintripgenerator.entities.StopEntity;

@Repository
public interface StopRepository extends JpaRepository<StopEntity, String> {
}
