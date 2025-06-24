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
public final class Route {

    @CsvBindByName(column = "route_id", required = true)
    private Long id;

    @CsvBindByName(column = "agency_id")
    private Long agencyId;

    @CsvBindByName(column = "route_short_name")
    private String routeShortName;

    @CsvBindByName(column = "route_long_name")
    private String routeLongName;

    @CsvBindByName(column = "route_desc")
    private String routeDesc;

    @CsvBindByName(column = "route_type")
    private Integer routeType;


}
