package ch.hack4rail.traintripgenerator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ch.hack4rail.traintripgenerator.gtfs.Route;
import ch.hack4rail.traintripgenerator.gtfs.Stop;
import ch.hack4rail.traintripgenerator.gtfs.StopTime;
import ch.hack4rail.traintripgenerator.gtfs.Trip;
import ch.hack4rail.traintripgenerator.repositories.RouteRepository;
import ch.hack4rail.traintripgenerator.repositories.StopRepository;
import ch.hack4rail.traintripgenerator.services.ParsingService;
import ch.hack4rail.traintripgenerator.services.mapping.RouteEntityMapper;
import ch.hack4rail.traintripgenerator.services.mapping.StopEntityMapper;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerApp implements CommandLineRunner {

    private final ParsingService parsingService;
    private final StopRepository stopRepository;
    private final RouteRepository routeRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("RUN command line runner!");

        var stops = parsingService.parseCSV("stops.txt", Stop.class);
        var stopTimes = parsingService.parseCSV("stop_times.txt", StopTime.class);
        var routes = parsingService.parseCSV("routes.txt", Route.class);
        var trips = parsingService.parseCSV("trips.txt", Trip.class);

        stopRepository.saveAll(stops.stream().map(StopEntityMapper::map).toList());
        routeRepository.saveAll(routes.stream().map(RouteEntityMapper::map).toList());

        System.out.println("stops: " + stops.size());
        System.out.println("times: " + stopTimes.size());
        System.out.println("routes: " + routes.size());
        System.out.println("trips: " + trips.size());
    }
}