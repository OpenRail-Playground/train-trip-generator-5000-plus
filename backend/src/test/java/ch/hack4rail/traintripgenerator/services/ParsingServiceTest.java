package ch.hack4rail.traintripgenerator.services;


import ch.hack4rail.traintripgenerator.gtfs.Route;
import ch.hack4rail.traintripgenerator.gtfs.Stop;
import ch.hack4rail.traintripgenerator.gtfs.StopTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(classes = ParsingService.class)
public class ParsingServiceTest {

    @Autowired
    private ParsingService parsingService;


    @Test
    public void testRoutesParsing() throws IOException {
        var parsed = parsingService.parseCSV("routes.txt", Route.class);
        Assertions.assertNotNull(parsed);
    }

    @Test
    public void testStopsParsing() throws IOException {
        var parsed = parsingService.parseCSV("stops.txt", Stop.class);
        Assertions.assertNotNull(parsed);
    }

    @Test
    public void testStopTimesParsing() throws IOException {
        var parsed = parsingService.parseCSV("stop_times.txt", StopTime.class);
        Assertions.assertNotNull(parsed);
    }


}
