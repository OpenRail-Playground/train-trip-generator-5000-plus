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
public final class Trip {

    @CsvBindByName(column = "trip_id", required = true)
    private String id;
    @CsvBindByName(column = "route_id")
    private String routeId;
    @CsvBindByName(column = "service_id")
    private String serviceId;
    @CsvBindByName(column = "trip_short_name")
    private String tripShortName;
    @CsvBindByName(column = "direction_id")
    private String directionId;
}
