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
public final class Stop {


    @CsvBindByName(column = "stop_id", required = true)
    private String id;

    @CsvBindByName(column = "stop_name")
    private String name;

    @CsvBindByName(column = "parent_station")
    private String parentStationId;

    @CsvBindByName(column = "stop_lat")
    private String latitude;

    @CsvBindByName(column = "stop_lon")
    private String longitude;

}
