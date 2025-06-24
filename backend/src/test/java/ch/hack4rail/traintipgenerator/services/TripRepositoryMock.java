package ch.hack4rail.traintipgenerator.services;

public class TripRepositoryMock {//implements TripRepository {

//	private List<Trip> trips = new ArrayList<>();
//
//	@Override
//	public List<Trip> findTripsDepartingIn(String departureStop) {
//		return trips.stream().filter(tripDepartsIn(departureStop)).toList();
//	}
//
//	private Predicate<Trip> tripDepartsIn(String departureStop) {
//		return trip -> trip.stops().stream().anyMatch(stop -> stop.stopName().equals(departureStop) && stop.departureTime() != null);
//	}
//	
//	private void addTrip(String routeName, String stop1, ZonedDateTime time1, String stop2, ZonedDateTime time2) {
//		trips.add(new Trip(routeName, List.of(start(stop1, time1), end(stop2, time2))));
//	}
//
//	private void addTrip(String routeName, String stop1, ZonedDateTime time1, String stop2, ZonedDateTime time2,
//			String stop3, ZonedDateTime time3) {
//		trips.add( new Trip(routeName, List.of(start(stop1, time1), stop(stop2, time2), end(stop3, time3))));
//	}
//
//	private void add(String routeName, String stop1, ZonedDateTime time1, String stop2, ZonedDateTime time2,
//			String stop3, ZonedDateTime time3, String stop4, ZonedDateTime time4) {
//		trips.add( new Trip(routeName, List.of(start(stop1, time1), stop(stop2, time2), stop(stop3, time3), end(stop4, time4))));
//	}
//
//	private Stop start(String stop, ZonedDateTime time) {
//		return new Stop(stop, null, time);
//	}
//
//	private Stop stop(String stop, ZonedDateTime time) {
//		return new Stop(stop, time, time.plusMinutes(10));
//	}
//
//	private Stop end(String stop, ZonedDateTime time) {
//		return new Stop(stop, time, null);
//	}
//
//	public static ZonedDateTime time(int hr, int min) {
//		return ZonedDateTime.of(LocalDate.now(), LocalTime.of(hr, min), ZonedDateTime.now().getZone());
//	}
//
//	public void duplicateForNextDays(int days) {
//		List<Trip> newTrips = new ArrayList<>();
//		for (Trip trip : trips) {
//			for (int i = 1; i < days; i++) {
//				final int addDays = i;
//				List<Stop> newStops = trip.stops().stream()
//						.map(stop -> new Stop(stop.stopName(),
//								stop.arrivalTime() == null ? null : stop.arrivalTime().plusDays(addDays),
//								stop.departureTime() == null ? null : stop.departureTime().plusDays(addDays)))
//						.toList();
//				newTrips.add(new Trip(trip.routeName(), newStops));
//			}
//		}
//		trips.addAll(newTrips);
//	}

}
