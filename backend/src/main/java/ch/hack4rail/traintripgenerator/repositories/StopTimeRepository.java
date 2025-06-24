package ch.hack4rail.traintripgenerator.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.hack4rail.traintripgenerator.entities.StopTimeEntity;

@Repository
public interface StopTimeRepository extends JpaRepository<StopTimeEntity, Long> {

	List<StopTimeEntity> findByStopParentStationId(Long parentStationId);

}
