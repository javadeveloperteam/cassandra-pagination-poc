package com.barath.cassandra.demo.app;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class PagingStoreService {
	
	private final PagingStateRepository pagingStateRepository;
	
	
	
	
	public PagingStoreService(PagingStateRepository pagingStateRepository) {
		super();
		this.pagingStateRepository = pagingStateRepository;
	}




	public PagingStateStore savePagingState(PagingStateStore pagingStateStore) {
		return this.pagingStateRepository.save(pagingStateStore);
	}
	
	public Optional<PagingStateStore> getPagingState(int pageId) {
		return this.pagingStateRepository.findByPageNo(pageId);
	}
	
	public List<PagingStateStore> getPagingStores(){
		return this.pagingStateRepository.findAll();
	}
	
	@PostConstruct
	public void init() {
		this.savePagingState(new PagingStateStore(1, null));
	}

}
