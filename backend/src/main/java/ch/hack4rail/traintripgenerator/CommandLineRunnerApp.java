package ch.hack4rail.traintripgenerator;

import ch.hack4rail.traintripgenerator.repositories.StopTimeRepository;
import ch.hack4rail.traintripgenerator.services.mapping.StopTimeEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
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

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerApp implements CommandLineRunner {

    private final ParsingService parsingService;
    private final StopRepository stopRepository;
    private final RouteRepository routeRepository;
    private final StopTimeRepository stopTimeRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("RUN command line runner!");

        var stops = parsingService.parseCSV("stops.txt", Stop.class);
        var stopTimes = parsingService.parseCSV("stop_times.txt", StopTime.class)
                .stream().filter(e -> {
                    val hours = Arrays.stream(e.getDepartureTime().split(":")).mapToInt(Integer::parseInt).findFirst().orElseThrow();
                    return hours < 24;
                }).toList();


        var routes = parsingService.parseCSV("routes.txt", Route.class);
        var trips = parsingService.parseCSV("trips.txt", Trip.class);

        stopRepository.saveAll(stops.stream().map(StopEntityMapper::map).toList());
        routeRepository.saveAll(routes.stream().map(RouteEntityMapper::map).toList());
        routeRepository.saveAll(routes.stream().map(RouteEntityMapper::map).toList());
        stopTimeRepository.saveAll(stopTimes.stream().map(StopTimeEntityMapper::map).toList());

        System.out.println("stops: " + stops.size());
        System.out.println("times: " + stopTimes.size());
        System.out.println("routes: " + routes.size());
        System.out.println("trips: " + trips.size());
    }
}