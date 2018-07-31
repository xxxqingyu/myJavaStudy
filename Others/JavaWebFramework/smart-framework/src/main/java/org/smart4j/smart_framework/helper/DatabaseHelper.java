package org.smart4j.smart_framework.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.smart_framework.util.CollectionUtil;
import org.smart4j.smart_framework.util.PropsUtil;



public class DatabaseHelper {
	private static final Logger LOGGER=LoggerFactory.getLogger(DatabaseHelper.class);
	private static final QueryRunner QUERY_RUNNER;
	private static final ThreadLocal<Connection> CONNECTION_HOLDER;
	private static final BasicDataSource DATA_SOURCE;
	

	static{
		CONNECTION_HOLDER=new ThreadLocal<>();
		QUERY_RUNNER=new QueryRunner();	
				
		Properties conf=PropsUtil.loadProps("config.properties");
		String driver=conf.getProperty("jdbc.driver");
		String url=conf.getProperty("jdbc.url");
		String username=conf.getProperty("jdbc.username");
		String password=conf.getProperty("jdbc.password");
		
		DATA_SOURCE=new BasicDataSource();
		DATA_SOURCE.setDriverClassName(driver);
		DATA_SOURCE.setUrl(url);
		DATA_SOURCE.setUsername(username);
		DATA_SOURCE.setPassword(password);
	}
	
	public static <T> List<T> queryEntityList(Class<T> entityClass,String sql,Object...params) {
		List<T> entityList;
		try{
			Connection connection=getConnection();
			entityList= QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass),params);
		}catch (SQLException e) {
			LOGGER.error("query entity list failure",e);
			throw new RuntimeException(e);
		}finally {
			//closeConnection();
		}
		return entityList;
	}
	
	public static <T> T queryEntity(Class<T> entityClass,String sql,Object...params){
		T entity;
		try{
			Connection connection=getConnection();
			entity= QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass),params);
		}catch (SQLException e) {
			LOGGER.error("query entity list failure",e);
			throw new RuntimeException(e);
		}finally {
			//closeConnection();
		}
		return entity;
	}
	
	public static List<Map<String, Object>> executeQuery(String sql,Object... params) {
		List<Map<String, Object>> result;
		try {
			Connection connection=getConnection();
			result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
		} catch (Exception e) {
			LOGGER.error("execute entity  failure",e);
			throw new RuntimeException(e);
		}finally {
			//closeConnection();
		}
		return result;
	}
	
	public static void executeSqlFile(String filePath) throws Exception {
		InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
		
		try{
		StringBuilder sqlBuilder=new StringBuilder();
		String line;
		while ((line=reader.readLine())!=null) {
			sqlBuilder.append(line);
		}
		
		DatabaseHelper.executeUpdate(sqlBuilder.toString());		
		}catch(Exception e){
			LOGGER.error("execute sql file failure",e);
			throw new RuntimeException(e);
		}
	}
	
	public static int executeUpdate(String sql,Object... params) {
		int rows;
		try {
			Connection connection=getConnection();
			rows = QUERY_RUNNER.update(connection, sql, params);
		} catch (Exception e) {
			LOGGER.error("execute entity  failure",e);
			throw new RuntimeException(e);
		}finally {
			//closeConnection();
		}
		return rows;
	}
	
	public static <T> boolean insertEntity(Class<T> entotyClass,Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not insert entity:field is empty");
			return false;
		}
		String sql ="INSERT INTO "+ getTableName(entotyClass);
		StringBuilder columns=new StringBuilder("(");
		StringBuilder values=new StringBuilder("(");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(", ");
			values.append("?, ");
		}
		columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
		values.replace(values.lastIndexOf(", "), values.length(), ")");
		
		sql+= columns+ " Values " + values;
		
		Object[] params=fieldMap.values().toArray();
		
		return executeUpdate(sql, params)==1;
	}
	
	public static String getTableName(Class<?> entityClass) {
		return entityClass.getSimpleName();
	}
	
	public static <T> boolean  updateEntity(Class<T> entityClass,long id,Map<String, Object> fieldMap)  {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not update entoty: fieldMap is empty");
			return false;
		}
		
		String sql="UPDATE "+getTableName(entityClass) + " SET ";
		StringBuilder columns = new StringBuilder();
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append("=?, ");
		}
		sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id=?";
		
		List<Object> paramList=new ArrayList<>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = paramList.toArray();
		
		return executeUpdate(sql, params) == 1;
	}
	
	public static <T> boolean deleteEntity(Class<T> entityClass,long id) {
		String sql="DELETE FROM "+getTableName(entityClass) + " WHERE id=? ";
		return executeUpdate(sql, id) == 1;
	}
	
	/**
	 * 开启事务
	 */
	public static void beginTransaction() {
		Connection connection= getConnection();
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 提交事务
	 */
	public static void commitTransaction() {
		Connection connection= getConnection();
		if (connection != null) {
			try {
				connection.commit();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}finally {
				closeConnection();
			}
		}
	}
	
	/**
	 * 回滚事务
	 */
	public static void rollbackTransaction() {
		Connection connection= getConnection();
		if (connection != null) {
			try {
				connection.rollback();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}finally {
				closeConnection();
			}
		}
	}
	/**
	 * 创建数据库链接
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection=CONNECTION_HOLDER.get();
		try {
			connection=  DATA_SOURCE.getConnection(); 
		} catch (SQLException e) {
			LOGGER.error("get connection failure",e);
		}finally {
			CONNECTION_HOLDER.set(connection);
		}
		return connection;
	}
	
	public static void closeConnection() {
		Connection connection=CONNECTION_HOLDER.get();
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.error("close connection failure",e);
			}finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
}
