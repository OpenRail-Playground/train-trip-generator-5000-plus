package ch.hack4rail.traintipgenerator;

import ch.hack4rail.traintipgenerator.gtfs.Route;
import ch.hack4rail.traintipgenerator.gtfs.Stop;
import ch.hack4rail.traintipgenerator.gtfs.StopTime;
import ch.hack4rail.traintipgenerator.gtfs.Trip;
import ch.hack4rail.traintipgenerator.services.ParsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerApp implements CommandLineRunner {

    private final ParsingService parsingService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("RUN command line runner!");

        var stops = parsingService.parseCSV("stops.txt", Stop.class);
        var stopTimes = parsingService.parseCSV("stop_times.txt", StopTime.class);
        var routes = parsingService.parseCSV("routes.txt", Route.class);
        var trips = parsingService.parseCSV("trips.txt", Trip.class);

        System.out.println("stops: " + stops.size());
        System.out.println("times: " + stopTimes.size());
        System.out.println("routes: " + routes.size());
        System.out.println("trips: " + trips.size());
    }
}