package com.barath.cassandra.demo.app;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Application implements Serializable{
	
	@PrimaryKeyColumn(name = "application_id",type = PrimaryKeyType.PARTITIONED)
	private Long applicationId;
	
	@Column(value = "application_name")
	private String applicationName;

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

	public Application(Long applicationId, String applicationName) {
		super();
		this.applicationId = applicationId;
		this.applicationName = applicationName;
	}

	public Application() {
		super();
		
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", applicationName=" + applicationName + "]";
	}
	
	

}
