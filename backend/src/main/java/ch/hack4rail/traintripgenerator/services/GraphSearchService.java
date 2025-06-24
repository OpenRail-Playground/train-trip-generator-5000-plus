package ch.hack4rail.traintripgenerator.services;

import static java.util.Comparator.comparing;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import ch.hack4rail.traintripgenerator.entities.StopTimeEntity;
import ch.hack4rail.traintripgenerator.entities.TripEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GraphSearchService {

	// TODO get repositories
	//	private final TripEntityRepository tripRepository;

	public List<TripEntity> getOptimalRoute(Long start, Long end, Duration maxDayTravelTime,
			LocalTime startOfTravelDay, Duration minimumConnectionTime) {
		LocalTime endOfTravelDay = startOfTravelDay.plus(maxDayTravelTime);
		LocalDate date = LocalDate.now();

		PriorityQueue<Node> queue = new PriorityQueue<>(comparing(Node::getTime));
		Set<Long> visitedStations = new HashSet<>();
		Map<Node, Node> parentMap = new HashMap<>();

		queue.add(new StartingNode(departureTime(date, startOfTravelDay), start));

		while (!queue.isEmpty()) {
			Node n = queue.poll();
			if (n.isArrival() && n.getStopId().equals(end)) {
				return getTripEntitys(parentMap, n);
			}
			if (n.isArrival()) {
				if (!visitedStations.add(n.getStopId())) {
					// Optional improvement: remove all other arrivals in this station from the
					// queue now
					continue;
				}
				// TODO get trips
				List<TripEntity> tripsDepartingIn = List.of(); // tripRepository.findTripEntitysDepartingIn(n.getStopId());
				// Optional improvement: only use a route once
				for (TripEntity trip : tripsDepartingIn) {
					if (trip.equals(n.trip)) {
						continue;
					}
					// TODO get stops
					// n.trip.stops().stream()
					Stream.<StopTimeEntity>of() //
							.filter(stop -> n.getStopId().equals(stop.getStopId())) //
							.filter(stop -> stop.getDepartureTime()
									.isAfter(n.getTime().toLocalTime().plus(minimumConnectionTime))) //
							.filter(stop -> stop.getDepartureTime().isBefore(endOfTravelDay)) //
							.findFirst() //
							.map(stop -> new Node(false, stop, trip, n.getTime().toLocalDate())) //
							.ifPresent(node -> {
								queue.add(node);
								parentMap.put(node, n);
							});
				}
				LocalDate nextDay = n.getTime().toLocalDate().plusDays(1);
				Node nextDayNode = new StartingNode(departureTime(nextDay, startOfTravelDay), n.getStopId());
				queue.add(nextDayNode);
				parentMap.put(nextDayNode, n);
			} else {
				boolean foundDeparture = false;
				// TODO get stops
				for (StopTimeEntity stop : List.<StopTimeEntity>of()) {// n.trip.stops()) {
					if (stop.getArrivalTime() != null && stop.getArrivalTime().isAfter(endOfTravelDay)) {
						break;
					}
					if (foundDeparture) {
						Node arrival = new Node(true, stop, n.trip, n.dateTime.toLocalDate());
						queue.add(arrival);
						parentMap.put(arrival, n);
					}
					if (stop.equals(n.getStopTimeEntity())) {
						foundDeparture = true;
					}
				}
			}
		}

		return null;
	}

	private LocalDateTime departureTime(LocalDate date, LocalTime startOfTravelDay) {
		return LocalDateTime.of(date, startOfTravelDay);
	}

	private List<TripEntity> getTripEntitys(Map<Node, Node> parentMap, Node n) {
		LinkedList<Node> nodes = getNodeList(parentMap, n);
		List<TripEntity> trips = new ArrayList<>();
		Node departure = null;
		for (Node node : nodes) {
			if (node instanceof StartingNode) {
				continue;
			}
			if (!node.isArrival()) {
				departure = node;
			} else {
				trips.add(trimTripEntity(departure, node));
				departure = null;
			}
		}
		return trips;
	}

	private TripEntity trimTripEntity(Node departure, Node arrival) {
		// TODO change to the REST api object
		//		TripEntity trip = departure.trip
		// TODO get all stopTimes
		LinkedList<StopTimeEntity> trimmedStopTimeEntitys = new LinkedList<>();// new LinkedList<>(trip.stops));
		while (!trimmedStopTimeEntitys.peekFirst().equals(departure.stop)) {
			trimmedStopTimeEntitys.removeFirst();
		}
		while (!trimmedStopTimeEntitys.peekLast().equals(arrival.stop)) {
			trimmedStopTimeEntitys.removeLast();
		}
		StopTimeEntity tripArrival = trimmedStopTimeEntitys.getLast();
		trimmedStopTimeEntitys.removeLast();
		trimmedStopTimeEntitys.add(
				new StopTimeEntity(tripArrival.getId(), tripArrival.getStopId(), tripArrival.getArrivalTime(), null));
		// TODO change to the REST api object
		return new TripEntity();
	}

	private LinkedList<Node> getNodeList(Map<Node, Node> parentMap, Node n) {
		LinkedList<Node> nodes = new LinkedList<>();
		Node node = n;
		while (node != null) {
			nodes.addFirst(node);
			node = parentMap.get(node);
		}
		return nodes;
	}

	private class Node {

		private boolean arrival;
		private LocalDateTime dateTime;
		private StopTimeEntity stop;
		private TripEntity trip;

		public Node(boolean arrival, StopTimeEntity stop, TripEntity trip, LocalDate date) {
			this.arrival = arrival;
			this.stop = stop;
			this.trip = trip;
			this.dateTime = LocalDateTime.of(date, arrival ? stop.getArrivalTime() : stop.getDepartureTime());
		}

		boolean isArrival() {
			return arrival;
		}

		StopTimeEntity getStopTimeEntity() {
			return stop;
		}

		LocalDateTime getTime() {
			return dateTime;
		}

		Long getStopId() {
			return stop.getStopId();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(arrival, trip, stop);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return arrival == other.arrival && Objects.equals(trip, other.trip) && Objects.equals(stop, other.stop);
		}

		private GraphSearchService getEnclosingInstance() {
			return GraphSearchService.this;
		}

	}

	private class StartingNode extends Node {

		private LocalDateTime time;
		private Long stopId;

		public StartingNode(LocalDateTime time, Long stopId) {
			super(true, null, null, null);
			this.time = time;
			this.stopId = stopId;
		}

		@Override
		LocalDateTime getTime() {
			return time;
		}

		@Override
		Long getStopId() {
			return stopId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(stopId, time);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			StartingNode other = (StartingNode) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return Objects.equals(stopId, other.stopId) && Objects.equals(time, other.time);
		}

		private GraphSearchService getEnclosingInstance() {
			return GraphSearchService.this;
		}

	}

}
