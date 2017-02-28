package com.jorge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //It is necessary to use Feign, an easy way to call RESTful services
@EnableHystrix //Using Hystrix, circuit breaker against microservices failures
@EnableHystrixDashboard //Hystrix monitoring dashboard
                        //1. Open http://localhost:8020/hystrix. 
                        //2. When prompted, enter http://localhost:8020/hystrix.stream as the host to monitor.
public class EurekaDiscoveryClientSentenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaDiscoveryClientSentenceApplication.class, args);
    }
}