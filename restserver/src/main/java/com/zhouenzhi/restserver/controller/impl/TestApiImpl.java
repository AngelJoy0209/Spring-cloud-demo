package com.zhouenzhi.restserver.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhouenzhi.restserver.controller.TestApi;
import com.zhouenzhi.restserver.service.RedisService;

@Controller
public class TestApiImpl implements TestApi {
	
	@Autowired
    private RedisService redisService;

	@ResponseBody
	public Map<String,String> testHello(@PathVariable("name") String name) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("vicky", "hello");
		return map;
	}

	@Override
	@ResponseBody
	public Map<String, String> testRedis() {
		StringBuffer sb = new StringBuffer();
        redisService.set("str", "str");
        sb.append("str=").append(redisService.get("str").toString()).append(",");
        redisService.hmSet("hmset","key","val");
        sb.append("hmset=").append(redisService.hmGet("hmset","key")).append(",");
        redisService.lPush("list","val");
        sb.append("list=").append(redisService.lRange("list",0,1).toString()).append(",");
        redisService.add("set","val");
        sb.append("set=").append(redisService.setMembers("set").toString()).append(",");
        redisService.zAdd("zset","val1",1);
        redisService.zAdd("zset","val2",2);
        sb.append("zset=").append(redisService.rangeByScore("zset",1,2)).append(",");
        Map<String,String> map = new HashMap<String,String>();
        map.put("123", sb.toString());
        return map;
	}

}
