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
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

import org.springframework.stereotype.Service;

import ch.hack4rail.traintripgenerator.entities.StopEntity;
import ch.hack4rail.traintripgenerator.entities.StopTimeEntity;
import ch.hack4rail.traintripgenerator.entities.TripEntity;
import ch.hack4rail.traintripgenerator.repositories.StopRepository;
import ch.hack4rail.traintripgenerator.repositories.StopTimeRepository;
import ch.hack4rail.traintripgenerator.repositories.TripRepository;
import ch.hack4rail.traintripgenerator.response.TripResponse;
import ch.hack4rail.traintripgenerator.response.TripResponsePart;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GraphSearchService {

	private final StopTimeRepository stopTimeRepository;
	private final StopRepository stopRepository;
	private final TripRepository tripRepository;

	public Optional<TripResponse> getOptimalRoute(Long startStopParentId, Long endStopParentId,
			Duration maxDayTravelTime, LocalTime startOfTravelDay, Duration minimumConnectionTime) {
		LocalTime endOfTravelDay = startOfTravelDay.plus(maxDayTravelTime);
		LocalDate date = LocalDate.now();

		PriorityQueue<Node> queue = new PriorityQueue<>(comparing(Node::getTime));
		Set<Long> visitedStations = new HashSet<>();
		Map<Node, Node> parentMap = new HashMap<>();

		queue.add(new StartingNode(departureTime(date, startOfTravelDay), startStopParentId));

		while (!queue.isEmpty()) {
			Node n = queue.poll();
			if (n.isArrival() && endStopParentId.equals(n.getParentStopId())) {
				return getTripResponse(parentMap, n);
			}
			if (n.isArrival()) {
				if (!visitedStations.add(n.getParentStopId()) && !(n instanceof StartingNode)) {
					continue;
				}
				List<Node> nextNodes = getNextNodesForArrival(n, minimumConnectionTime, startOfTravelDay,
						endOfTravelDay);
				if (nextNodes.isEmpty()) {
					visitedStations.remove(n.getParentStopId());
				}
				nextNodes.forEach(nextNode -> {
					queue.add(nextNode);
					parentMap.put(nextNode, n);
				});
			} else {
				List<Node> nextNodes = getNextNodesForDeparture(endOfTravelDay, n);
				nextNodes.forEach(nextNode -> {
					queue.add(nextNode);
					parentMap.put(nextNode, n);
				});
			}
		}

		return Optional.empty();
	}

	private List<Node> getNextNodesForDeparture(LocalTime endOfTravelDay, Node n) {
		List<StopTimeEntity> stopTimes = stopTimeRepository
				.findByTripIdOrdered(n.getStopTimeEntity().getId().getTripId());
		List<Node> nextNodes = new ArrayList<>();
		boolean foundDeparture = false;
		for (StopTimeEntity stop : stopTimes) {
			if (stop.getArrivalTime() != null && stop.getArrivalTime().isAfter(endOfTravelDay)) {
				break;
			}
			if (foundDeparture) {
				nextNodes.add(new Node(true, stop, n.dateTime.toLocalDate()));
			}
			if (stop.equals(n.getStopTimeEntity())) {
				foundDeparture = true;
			}
		}
		return nextNodes;
	}

	private List<Node> getNextNodesForArrival(Node n, Duration minimumConnectionTime, LocalTime startOfTravelDay,
			LocalTime endOfTravelDay) {
		List<Node> nextNodes = new ArrayList<>();
		List<StopTimeEntity> stopTimes = stopTimeRepository.findByStopParentStationId(n.getParentStopId());
		for (StopTimeEntity stopTime : stopTimes) {
			if (n.getStopTimeEntity() != null
					&& stopTime.getId().getTripId() == n.getStopTimeEntity().getId().getTripId()) {
				continue;
			}
			if (stopTime.getDepartureTime() == null
					|| stopTime.getDepartureTime().isBefore(n.getTime().toLocalTime().plus(minimumConnectionTime))) {
				continue;
			}
			if (!stopTime.getDepartureTime().isBefore(endOfTravelDay)) {
				continue;
			}
			nextNodes.add(new Node(false, stopTime, n.getTime().toLocalDate()));
		}
		LocalDate nextDay = n.getTime().toLocalDate().plusDays(1);
		nextNodes.add(new StartingNode(departureTime(nextDay, startOfTravelDay), n.getParentStopId()));
		return nextNodes;
	}

	private LocalDateTime departureTime(LocalDate date, LocalTime startOfTravelDay) {
		return LocalDateTime.of(date, startOfTravelDay);
	}

	private Optional<TripResponse> getTripResponse(Map<Node, Node> parentMap, Node n) {
		LinkedList<Node> nodes = getNodeList(parentMap, n);
		List<TripResponsePart> tripParts = new ArrayList<>();
		Node departure = null;
		for (Node node : nodes) {
			if (node instanceof StartingNode) {
				continue;
			}
			if (!node.isArrival()) {
				departure = node;
			} else {
				tripParts.add(trimTripEntity(departure, node));
				departure = null;
			}
		}
		return Optional.ofNullable(new TripResponse(tripParts));
	}

	private TripResponsePart trimTripEntity(Node departure, Node arrival) {
		StopTimeEntity departureStopTime = departure.getStopTimeEntity();
		StopTimeEntity arrivalStopTime = arrival.getStopTimeEntity();
		TripEntity trip = tripRepository.findById(departureStopTime.getId().getTripId()).orElseThrow();
		StopEntity departureStop = stopRepository.findById(departureStopTime.getStop().getParentStationId())
				.orElseThrow();
		StopEntity arrivalStop = stopRepository.findById(arrivalStopTime.getStop().getParentStationId()).orElseThrow();
		return new TripResponsePart(departureStop.getLatitude(), departureStop.getLongitude(),
				arrivalStop.getLatitude(), arrivalStop.getLongitude(), departureStop.getName(), arrivalStop.getName(),
				departure.getTime(), arrival.getTime(), trip.getRoute().getRouteShortName());
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
		private Long parentStopId;

		public Node(boolean arrival, StopTimeEntity stop, LocalDate date) {
			this.arrival = arrival;
			this.stop = stop;
			this.dateTime = stop == null ? null
					: LocalDateTime.of(date, arrival ? stop.getArrivalTime() : stop.getDepartureTime());
			this.parentStopId = stop == null ? null : stop.getStop().getParentStationId();
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

		Long getParentStopId() {
			return parentStopId;
		}

		@Override
		public String toString() {
			return (arrival ? "arrival: " : "departure: ") + stop.getStop().getName() + "(" + stop.getStop().getId()
					+ ") at " + dateTime.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(arrival, dateTime, parentStopId, stop);
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
			return arrival == other.arrival && Objects.equals(dateTime, other.dateTime)
					&& Objects.equals(parentStopId, other.parentStopId) && Objects.equals(stop, other.stop);
		}

		private GraphSearchService getEnclosingInstance() {
			return GraphSearchService.this;
		}

	}

	private class StartingNode extends Node {

		private LocalDateTime time;
		private Long stopParentId;

		public StartingNode(LocalDateTime time, Long stopParentId) {
			super(true, null, null);
			this.time = time;
			this.stopParentId = stopParentId;
		}

		@Override
		LocalDateTime getTime() {
			return time;
		}

		Long getParentStopId() {
			return stopParentId;
		}

		@Override
		public String toString() {
			return "StartNode: " + stopRepository.findById(stopParentId).get().getName() + "(" + stopParentId + ") at "
					+ time;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(stopParentId, time);
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
			return Objects.equals(stopParentId, other.stopParentId) && Objects.equals(time, other.time);
		}

		private GraphSearchService getEnclosingInstance() {
			return GraphSearchService.this;
		}

	}

}
