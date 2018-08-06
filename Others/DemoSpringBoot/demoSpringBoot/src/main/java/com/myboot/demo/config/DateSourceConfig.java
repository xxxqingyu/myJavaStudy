package com.myboot.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Primary;

public class DateSourceConfig {
	
	//@Resource
    //MasterDataSourceConfig masterDataSourceConfig;
	
	public MultiRouteDataSource exampleRouteDataSource() {
        MultiRouteDataSource multiDataSource = new MultiRouteDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        //targetDataSources.put("master", masterDataSource());
        //targetDataSources.put("first", firstDataSource());
        //targetDataSources.put("second", secondDataSource());
        multiDataSource.setTargetDataSources(targetDataSources);
        //multiDataSource.setDefaultTargetDataSource(masterDataSource());
        return multiDataSource;
	}
}
