package com.zhouenzhi.restserver.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface TestApi {
	
	@RequestMapping(value="/testhello/{name}", method=RequestMethod.GET)
	public Map<String,String> testHello(@PathVariable("name") String name);
	
	@RequestMapping(value="/test/redis",method=RequestMethod.GET)
	public Map<String,String> testRedis();
}
