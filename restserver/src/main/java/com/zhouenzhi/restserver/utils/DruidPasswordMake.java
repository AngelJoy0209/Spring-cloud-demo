package com.zhouenzhi.restserver.utils;

import java.lang.reflect.Method;

import com.alibaba.druid.filter.config.ConfigTools;

public class DruidPasswordMake {
	
	public static void main(String[] args) {
		String password = "viviangel";
		
		try {
			Method method = ConfigTools.class.getMethod("main", String[].class);
			method.invoke(null, (Object)new String[] {password});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
