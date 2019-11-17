package com.barath.cassandra.demo.app;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface EventRepository extends CassandraRepository<Event, Long>{
	
	List<Event> findByApplicationId(Long applicationId);
	
	Slice<Event> findByApplicationId(Long applicationId, Pageable pageRequest);
}
