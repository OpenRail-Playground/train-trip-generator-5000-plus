package ch.hack4rail.traintripgenerator.gtfs;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public final class StopTime {

    @CsvBindByName(column = "stop_id")
    private Long stopId;

    @CsvBindByName(column = "stop_sequence")
    private Integer stopSequence;

    @CsvBindByName(column = "trip_id")
    private Long tripId;

    @CsvBindByName(column = "departure_time")
    private String departureTime;

    @CsvBindByName(column = "arrival_time")
    private String arrivalTime;
}
