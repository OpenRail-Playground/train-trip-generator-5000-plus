package ch.hack4rail.traintripgenerator.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.hack4rail.traintripgenerator.entities.StopEntity;
import ch.hack4rail.traintripgenerator.entities.StopTimeEntity;
import ch.hack4rail.traintripgenerator.entities.StopTimeId;
import ch.hack4rail.traintripgenerator.entities.TripEntity;
import ch.hack4rail.traintripgenerator.response.TripResponse;

public class GraphSearchServiceTest {

	private TripRepositoryMock tripRepo;
	private StopRepositoryMock stopRepo;
	private StopTimeRepositoryMock stopTimeRepo;
	private GraphSearchService search;
	
	@BeforeEach
	public void setup() {
		tripRepo = new TripRepositoryMock();
		stopRepo = new StopRepositoryMock();
		stopTimeRepo = new StopTimeRepositoryMock();
		search = new GraphSearchService(stopTimeRepo, stopRepo, tripRepo);

		addTrip("IC1", "Koln", time(8, 0), "Bremen", time(12, 0), "Hamburg", time(15, 0));
		addTrip("IC2", "Koln", time(9, 0), "Munich", time(14, 0));
		addTrip("IC3", "Munich", time(10, 0), "Berlin", time(13, 0));
		addTrip("IC3", "Munich", time(15, 0), "Berlin", time(18, 0));
		addTrip("IC4", "Bremen", time(8, 0), "Osnabruck", time(9, 0), "Berlin", time(12, 0));
		addTrip("IC4", "Bremen", time(13, 0), "Osnabruck", time(14, 0), "Berlin", time(17, 0));
		addTrip("IC5", "Hamburg", time(16, 0), "Berlin", time(18, 0), "Dresden", time(19, 0));
		addTrip("IC5", "Hamburg", time(10, 0), "Berlin", time(12, 0), "Dresden", time(13, 0));
	}

	@Test
	public void testMultiDayTrip() {
		Optional<TripResponse> optimalRoute = search.getOptimalRoute(1L, 2L, Duration.ofHours(6), LocalTime.of(7, 0),
				Duration.ofMinutes(6));

		assertTrue(optimalRoute.isPresent());
//		assertEquals("IC1", optimalRoute.get().routeName());
//		assertEquals("Koln", optimalRoute.get().stops().get(0).stopName());
//		assertEquals("2025-06-24T08:00",
//				optimalRoute.get().stops().get(0).departureTime().toLocalDateTime().toString());
//		assertEquals("IC4", optimalRoute.get().routeName());
//		assertEquals("Bremen", optimalRoute.get().stops().get(0).stopName());
//		assertEquals("2025-06-25T08:00",
//				optimalRoute.get().stops().get(0).departureTime().toLocalDateTime().toString());
//		assertEquals("IC5", optimalRoute.get(2).routeName());
//		assertEquals("Berlin", optimalRoute.get(2).stops().get(0).stopName());
//		assertEquals("2025-06-25T12:10",
//				optimalRoute.get(2).stops().get(0).departureTime().toLocalDateTime().toString());
	}

	private int tripId = 1;

	private void addTrip(String routeName, String stop1, LocalTime time1, String stop2, LocalTime time2) {
		StopEntity parentStop1 = stopRepo.addParent(stop1);
		StopEntity childStop1 = stopRepo.addChild(stop1, parentStop1.getId());
		StopEntity parentStop2 = stopRepo.addParent(stop2);
		StopEntity childStop2 = stopRepo.addChild(stop2, parentStop2.getId());

		TripEntity trip = TripEntity.builder().id((long) tripId++).tripShortName(routeName).build();
		tripRepo.addTrip(trip);

		int sequence = 0;
		StopTimeEntity stopTime1 = StopTimeEntity.builder().departureTime(time1).stop(childStop1)
				.id(StopTimeId.builder().stopSequence(sequence++).tripId(trip.getId()).build()).build();
		StopTimeEntity stopTime2 = StopTimeEntity.builder().arrivalTime(time2).stop(childStop2)
				.id(StopTimeId.builder().stopSequence(sequence++).tripId(trip.getId()).build()).build();
		stopTimeRepo.addStopTimes(stopTime1, stopTime2);
	}

	private void addTrip(String routeName, String stop1, LocalTime time1, String stop2, LocalTime time2, String stop3,
			LocalTime time3) {
		StopEntity parentStop1 = stopRepo.addParent(stop1);
		StopEntity childStop1 = stopRepo.addChild(stop1, parentStop1.getId());
		StopEntity parentStop2 = stopRepo.addParent(stop2);
		StopEntity childStop2 = stopRepo.addChild(stop2, parentStop2.getId());
		StopEntity parentStop3 = stopRepo.addParent(stop3);
		StopEntity childStop3 = stopRepo.addChild(stop3, parentStop3.getId());

		TripEntity trip = TripEntity.builder().id((long) tripId++).tripShortName(routeName).build();
		tripRepo.addTrip(trip);

		int sequence = 0;
		StopTimeEntity stopTime1 = StopTimeEntity.builder().departureTime(time1).stop(childStop1)
				.id(StopTimeId.builder().stopSequence(sequence++).tripId(trip.getId()).build()).build();
		StopTimeEntity stopTime2 = StopTimeEntity.builder().departureTime(time2.plusMinutes(10)).arrivalTime(time2)
				.stop(childStop2).id(StopTimeId.builder().stopSequence(sequence++).tripId(trip.getId()).build())
				.build();
		StopTimeEntity stopTime3 = StopTimeEntity.builder().arrivalTime(time3).stop(childStop3)
				.id(StopTimeId.builder().stopSequence(sequence++).tripId(trip.getId()).build()).build();
		stopTimeRepo.addStopTimes(stopTime1, stopTime2, stopTime3);
	}

	private LocalTime time(int hr, int min) {
		return LocalTime.of(hr, min);
	}

}
