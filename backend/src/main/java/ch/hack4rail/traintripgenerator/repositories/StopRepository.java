package ch.hack4rail.traintripgenerator.repositories;

import ch.hack4rail.traintripgenerator.entities.StopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopRepository extends JpaRepository<StopEntity, Long> {

    @Query(value = """
                   SELECT * FROM stops
                  WHERE LOWER(NAME) LIKE LOWER(CONCAT('%', :stationName, '%'))
                            AND PARENT_STATION_ID IS NULL
                  ORDER BY NAME ASC
                  LIMIT 10
            """, nativeQuery = true)
    List<StopEntity> search(@Param("stationName") String stationName);

}
