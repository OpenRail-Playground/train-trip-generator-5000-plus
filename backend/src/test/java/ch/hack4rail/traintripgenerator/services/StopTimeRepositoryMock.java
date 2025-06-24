package ch.hack4rail.traintripgenerator.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import ch.hack4rail.traintripgenerator.entities.StopTimeEntity;
import ch.hack4rail.traintripgenerator.repositories.StopTimeRepository;

public class StopTimeRepositoryMock implements StopTimeRepository {

	private List<StopTimeEntity> stopTimes = new ArrayList<>();

	public void addStopTimes(StopTimeEntity... stopTimes) {
		this.stopTimes.addAll(Arrays.asList(stopTimes));
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends StopTimeEntity> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopTimeEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<StopTimeEntity> entities) {
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
	public StopTimeEntity getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StopTimeEntity getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StopTimeEntity getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopTimeEntity> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopTimeEntity> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopTimeEntity> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StopTimeEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StopTimeEntity> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopTimeEntity> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<StopTimeEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
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
	public void delete(StopTimeEntity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends StopTimeEntity> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StopTimeEntity> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<StopTimeEntity> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopTimeEntity> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends StopTimeEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StopTimeEntity> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends StopTimeEntity> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends StopTimeEntity, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StopTimeEntity> findByStopParentStationId(Long parentStationId) {
		return stopTimes.stream().filter(stopTime -> parentStationId.equals(stopTime.getStop().getParentStationId()))
				.toList();
	}

	@Override
	public List<StopTimeEntity> findByTripIdOrdered(Long tripId) {
		return stopTimes.stream() //
			.filter(stopTime -> tripId.equals(stopTime.getId().getTripId())) //
			.sorted(Comparator.comparing(stopTime -> ((StopTimeEntity) stopTime).getId().getStopSequence())) //
			.toList();
	}
}
