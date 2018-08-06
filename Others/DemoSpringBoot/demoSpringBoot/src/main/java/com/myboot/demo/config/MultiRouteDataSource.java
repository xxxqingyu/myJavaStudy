package com.myboot.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * @author Administrator
 * 多数据源切换
 */
public class MultiRouteDataSource extends AbstractRoutingDataSource  {
	  @Override
	   protected Object determineCurrentLookupKey() {
	        return "";//DataSourceContextHolder.getDataSource();
	   }
}
