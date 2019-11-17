package com.barath.cassandra.demo.app;

import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.PagingState;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;


@Service
public class EventService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final EventRepository eventRepository;
	private final CassandraOperations cassandraOperations;
	private final PagingStoreService pagingStoreService;
	private final Session session;

	public EventService(EventRepository eventRepository, CassandraOperations cassandraOperations,
			PagingStoreService pagingStoreService,
			Session session) {
		super();
		this.eventRepository = eventRepository;
		this.cassandraOperations= cassandraOperations;
		this.pagingStoreService= pagingStoreService;
		this.session= session;
	}
	
	
	public List<Event> getEvents(){
		return this.eventRepository.findAll();
	}
	
	public Event saveEvent(Event event) {
		return this.eventRepository.save(event);
	}
	
	public List<Event> saveEvents(List<Event> events){
		return this.eventRepository.saveAll(events);
	}
	
	public List<Event> getEventsByAppId(Long applicationId) {
		return this.eventRepository.findByApplicationId(applicationId);
	}
	
	public List<Event> getEventsByAppIdAndPageSize(Long applicationId, int pageNo, int size){
	    
	    if(logger.isInfoEnabled()) {
	    	logger.info("Get events by app id {}",applicationId);
	    }
		 
	    Select select = QueryBuilder
                .select()
                .from("event");
        select.where(QueryBuilder.eq("application_id", applicationId));
        if(size == 0) {
        	select.setFetchSize(5);
        }else {
        	select.setFetchSize(size);
        }
        CassandraPageRequest pageRequest =null;
        PagingState pagingState= null;
        if( pageNo == 0 || pageNo == 1) {
        	pageRequest = CassandraPageRequest.first(size);
        	pagingState= pageRequest.getPagingState();
        }else {
        	Optional<PagingStateStore> pagingStoreOpt = this.pagingStoreService.getPagingState(pageNo);
            if(pagingStoreOpt.isPresent()) {
            	PagingStateStore pagingStore = pagingStoreOpt.get();
            	if(pagingStore !=null && pagingStore.getPageState() != null) {
            		pagingState=PagingState.fromString(pagingStore.getPageState());
                }
            }
         }


        if (pagingState != null) {
        	logger.info("Paging state for PageNo {} ==> {}",pageNo,Objects.toString(pagingState));
            select.setPagingState(pagingState);
        }

        Slice<Event> events = cassandraOperations.slice(select, Event.class);

        logger.info("Found {} events with/by application id {}", events.getContent().size(), applicationId);

        if(events.hasNext()) {
            CassandraPageRequest next = (CassandraPageRequest) events.nextPageable();
            PagingStateStore store = new PagingStateStore();            
            store.setPageNo(next.getPageNumber()+1);         
            logger.info("Paging state store {}",Objects.toString(store));
            store.setPageState(Objects.toString(next.getPagingState()));
            this.pagingStoreService.savePagingState(store);
       }
     
        
        return events.isEmpty() ? Collections.emptyList() : events.getContent();
           
	}
	
	
	private void storePagingState(int pageNo,PagingState state) {

		this.pagingStoreService.savePagingState(new PagingStateStore(pageNo, Objects.toString(state)));
	}
	
	@PostConstruct
	public void init() throws InterruptedException {
		
		this.saveEvents(Arrays.asList(
				new Event(1L,"event11",1L,"app1",new Date()),
				new Event(1L,"event12",1L,"app1",new Date()),
				new Event(1L,"event13",1L,"app1",new Date()),
				new Event(1L,"event14",1L,"app1",new Date()),
				new Event(1L,"event15",1L,"app1",new Date()),
				new Event(1L,"event16",1L,"app1",new Date()),
				new Event(1L,"event17",1L,"app1",new Date()),
				new Event(1L,"event18",1L,"app1",new Date()),
				new Event(1L,"event19",1L,"app1",new Date()),
				new Event(1L,"event20",2L,"app2",new Date()),
				new Event(1L,"event21",2L,"app2",new Date()),
				new Event(1L,"event22",2L,"app2",new Date()),
				new Event(1L,"event23",2L,"app2",new Date()),
				new Event(1L,"event24",2L,"app2",new Date()),
				new Event(1L,"event25",2L,"app2",new Date()),
				new Event(1L,"event26",2L,"app2",new Date())));
		
		Thread.sleep(5000);
		

		this.saveEvents(Arrays.asList(
				new Event(1L,"event31",1L,"app1",new Date()),
				new Event(1L,"event32",1L,"app1",new Date()),
				new Event(1L,"event33",1L,"app1",new Date()),
				new Event(1L,"event34",1L,"app1",new Date()),
				new Event(1L,"event35",1L,"app1",new Date()),
				new Event(1L,"event36",1L,"app1",new Date()),
				new Event(1L,"event37",1L,"app1",new Date()),
				new Event(1L,"event38",1L,"app1",new Date()),
				new Event(1L,"event39",1L,"app1",new Date()),
				new Event(1L,"event40",2L,"app2",new Date()),
				new Event(1L,"event41",2L,"app2",new Date()),
				new Event(1L,"event42",2L,"app2",new Date()),
				new Event(1L,"event43",2L,"app2",new Date()),
				new Event(1L,"event44",2L,"app2",new Date()),
				new Event(1L,"event45",2L,"app2",new Date()),
				new Event(1L,"event46",2L,"app2",new Date())));
	}

	
	





}
