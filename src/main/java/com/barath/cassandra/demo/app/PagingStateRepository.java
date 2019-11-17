package com.barath.cassandra.demo.app;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface PagingStateRepository extends CassandraRepository<PagingStateStore, Integer>{
   
	Optional<PagingStateStore> findByPageNo(int pageNo);
}
