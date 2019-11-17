package com.barath.cassandra.demo.app;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class PagingStateStore {
	
	@PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
	private int pageNo;
	
	private String pageState;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageState() {
		return pageState;
	}

	public void setPageState(String pageState) {
		this.pageState = pageState;
	}

	public PagingStateStore(int pageNo, String pageState) {
		super();
		this.pageNo = pageNo;
		this.pageState = pageState;
	}

	public PagingStateStore() {
		super();
		
	}
	
	

}
