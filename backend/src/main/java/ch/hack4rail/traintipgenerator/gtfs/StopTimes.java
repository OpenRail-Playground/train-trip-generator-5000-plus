package ch.hack4rail.traintipgenerator.gtfs;

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
public final class StopTimes {

    @CsvBindByName(column = "stop_id")
    private String stopId;

    @CsvBindByName(column = "stop_sequence")
    private String stopSequence;

    @CsvBindByName(column = "trip_id")
    private String tripId;

    @CsvBindByName(column = "departure_time")
    private String departureTime;

    @CsvBindByName(column = "arrival_time")
    private String arrivalTime;
}
