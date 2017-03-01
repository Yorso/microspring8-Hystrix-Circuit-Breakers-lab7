package com.jorge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/*
 * Turbine is a tool for aggregating streams of Server-Sent Event (SSE) JSON data into a single stream. 
 * The targeted use case is metrics streams from instances in an SOA being aggregated for dashboards.
 * 
 * Turbine listens and adds all active Hystrix service streams, interacting with Eureka
 * 
 * Open http://localhost:8020/hystrix. When prompted, enter 
 * http://localhost:8030/turbine.stream as the host to monitor. 
 * 
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@EnableTurbine
public class TurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
    
//	@Bean
//	public TurbineProperties turbineProperties() {
//		return new TurbineProperties();
//	}
//
//	@Bean
//	public InstanceDiscovery instanceDiscovery() {
//		return new ConfigPropertyBasedDiscovery();
//	}
    
}