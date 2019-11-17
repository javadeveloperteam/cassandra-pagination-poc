package com.barath.cassandra.demo.app;

import java.util.Date;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Event {
	
	@PrimaryKeyColumn(name = "event_id",type = PrimaryKeyType.CLUSTERED)
	private Long eventId;
	
	@PrimaryKeyColumn(name = "event_name",type = PrimaryKeyType.CLUSTERED)
	private String eventName;
	
	@PrimaryKeyColumn(name = "application_id",type = PrimaryKeyType.PARTITIONED)
	private Long applicationId;
	
	
	@Column(value="application_name")
	private String applicationName;
	
	@PrimaryKeyColumn(name = "event_occured",type = PrimaryKeyType.CLUSTERED,ordering = Ordering.DESCENDING)
	private Date eventOccured;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	
	

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}	

	public Date getEventOccured() {
		return eventOccured;
	}

	public void setEventOccured(Date eventOccured) {
		this.eventOccured = eventOccured;
	}

	public Event() {
		super();
		
	}

	public Event(Long eventId, String eventName, Long applicationId, String applicationName, Date eventOccured) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.applicationId = applicationId;
		this.applicationName = applicationName;
		this.eventOccured = eventOccured;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", applicationId=" + applicationId
				+ ", applicationName=" + applicationName + ", eventOccured=" + eventOccured + "]";
	}	
	
	
	

}
