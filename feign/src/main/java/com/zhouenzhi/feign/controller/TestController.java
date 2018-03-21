package com.zhouenzhi.feign.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient balancerClient;
	
	/**
	 * 这是一个说明
	 * @param map
	 * @return
	 */
	@GetMapping(value="/test")
	@ResponseBody
	public Map<String,String> test() {
		
		ServiceInstance instance = balancerClient.choose("eureka.server");
		String url = instance.getUri().toString()+"/testhello/lalal";
		URI uri = URI.create(url);
		Map<String,String> map = restTemplate.getForObject(uri, Map.class);
		return map;
	}
}
