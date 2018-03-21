package com.zhouenzhi.restserver;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
public class EurekaClientDemo {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaClientDemo.class).web(true).run(args);
	}

}
