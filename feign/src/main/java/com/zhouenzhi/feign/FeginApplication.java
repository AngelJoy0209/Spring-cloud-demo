package com.zhouenzhi.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class FeginApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeginApplication.class, args);
	}
	
	@LoadBalanced
	public RestTemplate loadBalanced() {
		return new RestTemplate();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
