package ch.hack4rail.traintipgenerator.services;


import ch.hack4rail.traintripgenerator.TrainTripGeneratorApplication;
import ch.hack4rail.traintripgenerator.gtfs.Route;
import ch.hack4rail.traintripgenerator.gtfs.Stop;
import ch.hack4rail.traintripgenerator.gtfs.Trip;
import ch.hack4rail.traintripgenerator.services.ParsingService;
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
        var trips = parsingService.parseCSV("routes.txt", Route.class);
        Assertions.assertNotNull(trips);
    }

    @Test
    public void testStopsParsing() throws IOException {
        var trips = parsingService.parseCSV("stops.txt", Stop.class);
        Assertions.assertNotNull(trips);
    }


}
