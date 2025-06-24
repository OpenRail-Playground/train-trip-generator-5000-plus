package ch.hack4rail.traintipgenerator.services;

public class GraphSearchServiceTest {

//	private TripRepositoryMock tripRepo;
//	private GraphSearchService search;
//
//	@BeforeEach
//	public void setup() {
//		tripRepo = new TripRepositoryMock();
//		search = new GraphSearchService(tripRepo);
//
//		tripRepo.addTrip("IC1", "Koln", time(8, 0), "Bremen", time(12, 0), "Hamburg", time(15, 0));
//		tripRepo.addTrip("IC2", "Koln", time(9, 0), "Munich", time(14, 0));
//		tripRepo.addTrip("IC3", "Munich", time(10, 0), "Berlin", time(13, 0));
//		tripRepo.addTrip("IC3", "Munich", time(15, 0), "Berlin", time(18, 0));
//		tripRepo.addTrip("IC4", "Bremen", time(8, 0), "Osnabruck", time(9, 0), "Berlin", time(12, 0));
//		tripRepo.addTrip("IC4", "Bremen", time(13, 0), "Osnabruck", time(14, 0), "Berlin", time(17, 0));
//		tripRepo.addTrip("IC5", "Hamburg", time(16, 0), "Berlin", time(18, 0), "Dresden", time(19, 0));
//		tripRepo.addTrip("IC5", "Hamburg", time(10, 0), "Berlin", time(12, 0), "Dresden", time(13, 0));
//		
//		tripRepo.duplicateForNextDays(2);
//	}
//	
//	@Test
//	public void testMultiDayTrip() {
//		List<Trip> optimalRoute = search.getOptimalRoute("Koln", "Dresden", Duration.ofHours(6), LocalTime.of(7, 0), Duration.ofMinutes(6));
//		
//		assertEquals(3, optimalRoute.size());
//		assertEquals("IC1", optimalRoute.get(0).routeName());
//		assertEquals("Koln", optimalRoute.get(0).stops().get(0).stopName());
//		assertEquals("2025-06-24T08:00", optimalRoute.get(0).stops().get(0).departureTime().toLocalDateTime().toString());
//		assertEquals("IC4", optimalRoute.get(1).routeName());
//		assertEquals("Bremen", optimalRoute.get(1).stops().get(0).stopName());
//		assertEquals("2025-06-25T08:00", optimalRoute.get(1).stops().get(0).departureTime().toLocalDateTime().toString());
//		assertEquals("IC5", optimalRoute.get(2).routeName());
//		assertEquals("Berlin", optimalRoute.get(2).stops().get(0).stopName());
//		assertEquals("2025-06-25T12:10", optimalRoute.get(2).stops().get(0).departureTime().toLocalDateTime().toString());
//	}

}
