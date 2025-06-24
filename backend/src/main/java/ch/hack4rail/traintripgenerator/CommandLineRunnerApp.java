package ch.hack4rail.traintripgenerator;

import ch.hack4rail.traintripgenerator.gtfs.Route;
import ch.hack4rail.traintripgenerator.gtfs.Stop;
import ch.hack4rail.traintripgenerator.gtfs.StopTime;
import ch.hack4rail.traintripgenerator.gtfs.Trip;
import ch.hack4rail.traintripgenerator.repositories.RouteRepository;
import ch.hack4rail.traintripgenerator.repositories.StopRepository;
import ch.hack4rail.traintripgenerator.repositories.StopTimeRepository;
import ch.hack4rail.traintripgenerator.repositories.TripRepository;
import ch.hack4rail.traintripgenerator.services.ParsingService;
import ch.hack4rail.traintripgenerator.services.mapping.RouteEntityMapper;
import ch.hack4rail.traintripgenerator.services.mapping.StopEntityMapper;
import ch.hack4rail.traintripgenerator.services.mapping.StopTimeEntityMapper;
import ch.hack4rail.traintripgenerator.services.mapping.TripEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Profile("import")
@Slf4j
public class CommandLineRunnerApp implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CommandLineRunnerApp.class);

    private final ParsingService parsingService;
    private final StopRepository stopRepository;
    private final RouteRepository routeRepository;
    private final StopTimeRepository stopTimeRepository;
    private final TripRepository tripRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("RUN command line runner!");

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
        stopTimeRepository.saveAll(stopTimes.stream().map(StopTimeEntityMapper::map).toList());
        tripRepository.saveAll(trips.stream().map(TripEntityMapper::map).toList());

        logger.info("FINISHED Parsing!");
        logger.info("stops: " + stops.size());
        logger.info("times: " + stopTimes.size());
        logger.info("routes: " + routes.size());
        logger.info("trips: " + trips.size());
    }
}