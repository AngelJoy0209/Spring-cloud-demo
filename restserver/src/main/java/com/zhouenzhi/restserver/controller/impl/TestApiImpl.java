package com.zhouenzhi.restserver.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhouenzhi.restserver.controller.TestApi;

@Controller
public class TestApiImpl implements TestApi {

	@ResponseBody
	public Map<String,String> testHello(@PathVariable("name") String name) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("vicky", "hello");
		return map;
	}

}
