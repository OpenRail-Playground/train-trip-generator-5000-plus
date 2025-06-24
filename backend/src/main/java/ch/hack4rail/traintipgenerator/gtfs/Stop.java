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
public final class Stop {


    @CsvBindByName(column = "stop_id")
    private String stop_id;


}
