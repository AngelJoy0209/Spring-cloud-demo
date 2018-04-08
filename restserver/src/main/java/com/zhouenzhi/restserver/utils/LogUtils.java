package com.zhouenzhi.restserver.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**  
 * @Title: LogUtils.class
 * @Description: 日志打印类，实现日志的打印
 * @author Bloodline Last 
 * @date 2016-08-09 14:19:00
 * @version V1.0  
*/
public class LogUtils {
	private static Logger logger = null;
	static{
		logger = LogManager.getLogger(LogUtils.class);
	}
	
	/**
	 * 普通信息打印
	 * @param msg
	 */
	public static void info(String msg) {
		logger.info(msg);
	}
	
	/**
	 * 调试信息打印
	 * @param msg
	 */
	public static void debug(String msg) {
		logger.debug(msg);
	}
	
	/**
	 * 警告信息打印
	 * @param msg
	 */
	public static void warn(String msg) {
		logger.warn(msg);
	}
	
	/**
	 * 错误信息打印
	 * @param msg
	 */
	public static void error(String msg) {
		logger.error(msg);
	}
}
