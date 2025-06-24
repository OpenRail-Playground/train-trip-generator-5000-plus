package ch.hack4rail.traintipgenerator.gtfs;

import com.opencsv.bean.CsvBindByName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Stop {


    @CsvBindByName()
    private String stop_id;


}
