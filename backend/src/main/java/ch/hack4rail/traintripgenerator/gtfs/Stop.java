package ch.hack4rail.traintripgenerator.gtfs;

import com.fasterxml.jackson.core.io.schubfach.FloatToDecimal;
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
    private Long id;

    @CsvBindByName(column = "stop_name")
    private String name;

    @CsvBindByName(column = "parent_station",  required = false)
    private Long parentStationId;

    @CsvBindByName(column = "stop_lat")
    private Float latitude;

    @CsvBindByName(column = "stop_lon")
    private Float longitude;

}
