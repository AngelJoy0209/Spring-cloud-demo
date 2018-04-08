package com.zhouenzhi.restserver.interceptor;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.zhouenzhi.restserver.utils.LogUtils;


@Intercepts(value={
	@Signature (type=Executor.class, method="update", args={MappedStatement.class, Object.class}),
    @Signature(type=Executor.class, method="query", args={MappedStatement.class, Object.class,RowBounds.class, 
    	ResultHandler.class, CacheKey.class,BoundSql.class}),
    @Signature(type=Executor.class, method="query", args={MappedStatement.class, Object.class, RowBounds.class, 
    	ResultHandler.class})})
public class SqlPrintInterceptor implements Interceptor {
	
	/**
	 * 当mybatis中的xml被解析成sql语句时，调用此方法
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		Object parameter = null;
		Object object[] = invocation.getArgs();
		MappedStatement statement = (MappedStatement) object[0];
		if(object.length > 1) {
			parameter = object[1];
		}
		
		long startTime = System.currentTimeMillis();
		
		String sqlId = statement.getId();
		Configuration configuration = statement.getConfiguration();
		BoundSql boundSql = statement.getBoundSql(parameter);
		String sql = getSql(configuration, boundSql);
		LogUtils.info(sqlId + ":***********************执行中*************************");
		LogUtils.info(sql);
		
		Object result = invocation.proceed();
		long endTime = System.currentTimeMillis();
		long spendTime = endTime - startTime;
		if(spendTime > 0) {
			LogUtils.info(sqlId + "共耗时:" + spendTime + "ms");
		}
		
		return result;
	}
	
	private String getSql(Configuration configuration, BoundSql boundSql) {
		String sql = showSql(configuration, boundSql);
		return sql;
	}
	
	private String showSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> mappingList = boundSql.getParameterMappings();
//		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		String sql = boundSql.getSql();
		if(mappingList.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
			if(registry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
			}else {
				MetaObject object = configuration.newMetaObject(parameterObject);
				for(ParameterMapping mapping : mappingList) {
					String propertyName = mapping.getProperty();
					if(object.hasGetter(propertyName)) {
						Object proObject = object.getValue(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(proObject));
					}else if(boundSql.hasAdditionalParameter(propertyName)) {
						Object proObject = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(proObject));
					}
				}
			}
		}
		
		return sql;
	}
	
	private String getParameterValue(Object object) {
		String value = "";
		if(object instanceof String) {
			value = "'" + object.toString() + "'";
		}else if(object instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		}else {
			if(object != null) {
				value = object.toString();
			}else {
				value = "";
			}
		}
		
		return value;
	}
	
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}
	
	public void setProperties(Properties properties) {
		
	}
}
