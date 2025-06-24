package ch.hack4rail.traintripgenerator.repositories;

import ch.hack4rail.traintripgenerator.entities.StopTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopTimeRepository extends JpaRepository<StopTimeEntity, Long> {

	List<StopTimeEntity> findByStopParentStationId(Long parentStationId);

	@Query("select u from stop_times u where u.id.tripId = ?1 order by u.id.stopSequence asc")
	List<StopTimeEntity> findByTripIdOrdered(Long tripId);

}
