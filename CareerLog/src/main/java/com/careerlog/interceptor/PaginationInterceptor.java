package com.careerlog.interceptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.List;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.log4j.Logger;

import com.careerlog.util.ReflectUtil;
import com.careerlog.entity.Page;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PaginationInterceptor implements Interceptor{

	private String databaseType;
	private static Logger log = Logger.getLogger(PaginationInterceptor.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//get RoutingStatementHandler from Invocation passed by Plugin.invoke()
		RoutingStatementHandler statementHandler = (RoutingStatementHandler)invocation.getTarget();
		
		//get value of parameter "delegate" from StatementHandler
		StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(statementHandler,"delegate");
		
		//get BoundSql from StatementHandler, BoundSql contains everything about Sql Statement
		BoundSql boundSql = delegate.getBoundSql();
		
		//get parameterObject form BoundSql, parameterObject are parameters (parameterType) that we've passed to Mapper.xml
		Object parameterObject = boundSql.getParameterObject();
		
		if(parameterObject instanceof Page<?>){
			Page<?> page = (Page<?>) parameterObject;
			//get MappedStatement from BaseStatementHandler
			MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");

			Connection connection =  (Connection) invocation.getArgs()[0];
			//get Sql statement that passed from Mapper.xml
			String sql = boundSql.getSql();
			
			//get total records and putting count value in Page
			this.setTotalRecord(page,mappedStatement,connection);
			
			String pageSql = null;
			StringBuffer sqlBuffer = new StringBuffer(sql);
			if("mysql".equalsIgnoreCase(databaseType))
				pageSql = getMysqlPageSql(page,sqlBuffer);
			else if("oracle".equalsIgnoreCase(databaseType))
				pageSql = getOraclePageSql(page,sqlBuffer);
			ReflectUtil.setFieldValue(boundSql,"sql",pageSql);
			log.info("intercepted page sql is"+pageSql.toString());
		}
		return invocation.proceed();
	}

	//called by This intercepter, wrapping together target object and This intercepter.
	//interceptorChain.pluginAll(StatementHandler)----> target=interceptor.plugin(target)
	@Override
	public Object plugin(Object target) {
	 return	Plugin.wrap(target, this);
		
	}
	
	
	//this method will be called before loading of configuration file.
	@Override
	public void setProperties(Properties properties) {
		this.databaseType = properties.getProperty("databaseType") == null ? "mysql" : properties.getProperty("databaseType");
		
	}
	
	//query total number of records match the searching condition.
	private void setTotalRecord(Page<?> page, MappedStatement mappedStatement, Connection connection){
		//get BoundSql from MappedStatement or delegate(StatementHandler)
		BoundSql boundSql = mappedStatement.getBoundSql(page);
		
		// get Sql statement that passed from Mapper.xml
		String sql = boundSql.getSql();
		
		//constructing a Sql statement for total records from selection Sql statement
		int index = sql.toUpperCase().indexOf("FROM");
		String countSql = "select COUNT(*) " + sql.substring(index);
		
		//get list of parameter mappings from BoundSql
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		
		//constructing a new BoundSql object for total records
		BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);
		
		//constructing a new parameterHandler object for setting the parameters into PreparedStatement
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(countSql);
			//setting the parameters into preparedStatement for total records counting
			parameterHandler.setParameters(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				//get count value from ResultSet (only 1 column)
				int totalRecord = resultSet.getInt(1);
				//set the counting value into Bean Page.
				page.setTotalRecord(totalRecord);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					//closing job
					if(resultSet != null)
						resultSet.close();
					if(preparedStatement != null)
						preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	private String getMysqlPageSql(Page<?> page, StringBuffer sqlBuffer){
		//the position of first records, index of records in Mysql database starts from 0
		int offset = (page.getStart() - 1) * page.getPageSize();
		sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());
		return sqlBuffer.toString();
	}
	
	
	private String getOraclePageSql(Page<?> page, StringBuffer sqlBuffer){
		//the position of first records, rownum starts from 1 in Oracle database
		int offset = (page.getStart() -1) * page.getPageSize() + 1;
		//
		// write some ugly sql statement for Oracle
		//
		return sqlBuffer.toString();
	}

}
