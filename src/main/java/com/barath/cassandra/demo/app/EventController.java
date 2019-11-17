package com.barath.cassandra.demo.app;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final EventService eventService;
	

	public EventController(EventService eventService) {
		super();
		this.eventService = eventService;
	}
	
	@GetMapping("/events")
	public List<Event> events(){
		return this.eventService.getEvents();
	}
	
	@GetMapping("/events/page")
	public List<Event> eventsByPage(@RequestParam int pageNo, @RequestParam int size, @RequestParam Long applicationId){
		
		if(logger.isInfoEnabled()) {
			logger.info("Get Events by applicationId {} pageNO {} size {}",applicationId,pageNo,size);
		}
		return this.eventService.getEventsByAppIdAndPageSize(applicationId, pageNo, size);
	}
	
	

}
