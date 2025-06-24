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
public final class Agency {

    @CsvBindByName(column = "agency_id", required = true)
    private Long id;

    @CsvBindByName(column = "agency_name")
    private String name;

    @CsvBindByName(column = "agency_url")
    private String url;

    @CsvBindByName(column = "agency_timezone")
    private String timezone;

    @CsvBindByName(column = "agency_lang")
    private String language;

}
