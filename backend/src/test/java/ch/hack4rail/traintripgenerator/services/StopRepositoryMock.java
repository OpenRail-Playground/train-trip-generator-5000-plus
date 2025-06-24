package ch.hack4rail.traintripgenerator.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import ch.hack4rail.traintripgenerator.entities.StopEntity;
import ch.hack4rail.traintripgenerator.repositories.StopRepository;

public class StopRepositoryMock implements StopRepository {

	private int stopId = 1;
	private List<StopEntity> stops = new ArrayList<>();

	public StopEntity addParent(String stopName) {
		return stops.stream().filter(stop -> stop.getName().equals(stopName)) //
				.filter(stop -> stop.getParentStationId() == null) //
				.findFirst().orElseGet(() -> {
					StopEntity stopEntity = StopEntity.builder().id((long) stopId++).name(stopName).build();
					stops.add(stopEntity);
					return stopEntity;
				});
	}

	public StopEntity addChild(String stopName, Long parentId) {
		return stops.stream().filter(stop -> stop.getName().equals(stopName)) //
				.filter(stop -> stop.getParentStationId() != null) //
				.findFirst().orElseGet(() -> {
					StopEntity stopEntity = StopEntity.builder().id((long) stopId++).parentStationId(parentId)
							.name(stopName).build();
					stops.add(stopEntity);
					return stopEntity;
				});
	}

	public void addStop(StopEntity... stops) {
		this.stops.addAll(Arrays.asList(stops));
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends StopEntity> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<StopEntity> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public StopEntity getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StopEntity getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StopEntity getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopEntity> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopEntity> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopEntity> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StopEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StopEntity> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopEntity> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<StopEntity> findById(Long id) {
		return stops.stream().filter(stop -> id.equals(stop.getId())).findFirst();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(StopEntity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends StopEntity> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StopEntity> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<StopEntity> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopEntity> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends StopEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopEntity> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends StopEntity> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends StopEntity, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StopEntity> search(String stationName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Long findIdByName(String name) {
		return stops.stream().filter(stop -> stop.getName().equals(name)).filter(stop -> stop.getParentStationId() == null).findFirst().map(StopEntity::getId).orElseThrow();
	}

}
