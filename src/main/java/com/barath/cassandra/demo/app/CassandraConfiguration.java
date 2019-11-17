package com.barath.cassandra.demo.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.QueryLogger;


/**
 * The Class CassandraConfiguration.
 */
@Configuration
public class CassandraConfiguration {

	/**
	 * Query logger.
	 *
	 * @param cluster the cluster
	 * @return the query logger
	 */
	@Bean
	public QueryLogger queryLogger(Cluster cluster) {
		QueryLogger queryLogger = QueryLogger.builder().build();
		cluster.register(queryLogger);
		return queryLogger;
	}

}
