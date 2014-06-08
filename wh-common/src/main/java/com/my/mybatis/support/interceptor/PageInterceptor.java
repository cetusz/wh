package com.my.mybatis.support.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.builder.xml.dynamic.ForEachSqlNode;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.MyBatisSystemException;

import com.my.mybatis.support.Page;



/**
 * 
 * <p>
 * 判断如果参数中有 {@link Page} 对象，那么执行分页查询。(1.查询总数并放入page对象中。 2.构造带有limit子句的sql替换原始的sql)
 * </p>
 * <p>
 * 目前只支持把page放到HashMap中(或使用接口时，把page作为方法的参数),并且key为"page"
 * </p>
 * 
 * @author 904032
 * @date 2014-2-14
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
@SuppressWarnings("rawtypes")
public class PageInterceptor implements Interceptor {

	private static final ThreadLocal<Page> PAGE_CONTEXT = new ThreadLocal<Page>();

	public static final String PAGE_KEY = "page";
	
	/**hsqldb,mysql,oracle...*/
	private String dialect = "";

	public Object intercept(Invocation ivk) throws Throwable {
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk
					.getTarget();
			BaseStatementHandler handler = (BaseStatementHandler) ReflectHelper
					.getValueByFieldName(statementHandler, "delegate");
			MappedStatement ms = (MappedStatement) ReflectHelper
					.getValueByFieldName(handler, "mappedStatement");

			BoundSql boundSql = handler.getBoundSql();
			Object parameterObject = boundSql.getParameterObject();
			String sql = boundSql.getSql();
            
			if (parameterObject instanceof HashMap) 
			{
				HashMap map = (HashMap) parameterObject;
				
				if(map.containsKey(PAGE_KEY))
				{
					Object pageObj = map.get(PAGE_KEY);
					if(pageObj!=null)
					{
						Page p = (Page)pageObj ;
						p.setTotal(queryTotal(ivk, ms, boundSql, parameterObject, sql));
						set(p);
						ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql(sql, p));
					}
				}
	

			}
		}
		return ivk.proceed();
	}

	/**
	 * 
	 * <p>
	 * {@link ResultSetInterceptor}获取一次即清空
	 * </p>
	 * 
	 * @return
	 */
	public static Page getPage() {
		Page p = PAGE_CONTEXT.get();
		PAGE_CONTEXT.remove();
		return p;
	}

	/**
	 * 
	 * <p>
	 * 保存在ThreadLocal中，使 {@link ResultSetInterceptor}能获取到此page对象
	 * </p>
	 * 
	 * @param p
	 */
	private static void set(Page p) {
		PAGE_CONTEXT.set(p);
	}

	/**
	 * 为count语句设置参数.
	 * 
	 * @see org.apache.ibatis.executor.parameter.DefaultParameterHandler#setParameters(PreparedStatement)
	 * 
	 * @param ps
	 * @param ms
	 * @param bs
	 * @param parameterObject
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	private void setParameters(PreparedStatement ps, MappedStatement ms,
			BoundSql bs, Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters")
				.object(ms.getParameterMap().getId());
		List<ParameterMapping> mappings = bs.getParameterMappings();
		if (mappings == null) {
			return;
		}
		Configuration configuration = ms.getConfiguration();
		TypeHandlerRegistry typeHandlerRegistry = configuration
				.getTypeHandlerRegistry();
		MetaObject metaObject = parameterObject == null ? null : configuration
				.newMetaObject(parameterObject);
		for (int i = 0; i < mappings.size(); i++) {
			ParameterMapping parameterMapping = mappings.get(i);
			if (parameterMapping.getMode() != ParameterMode.OUT) {
				Object value;
				String propertyName = parameterMapping.getProperty();
				PropertyTokenizer prop = new PropertyTokenizer(propertyName);
				if (parameterObject == null) {
					value = null;
				} else if (typeHandlerRegistry.hasTypeHandler(parameterObject
						.getClass())) {
					value = parameterObject;
				} else if (bs.hasAdditionalParameter(propertyName)) {
					value = bs.getAdditionalParameter(propertyName);
				} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
						&& bs.hasAdditionalParameter(prop.getName())) {
					value = bs.getAdditionalParameter(prop.getName());
					if (value != null) {
						value = configuration.newMetaObject(value)
								.getValue(
										propertyName.substring(prop.getName()
												.length()));
					}
				} else {
					value = metaObject == null ? null : metaObject
							.getValue(propertyName);
				}
				TypeHandler typeHandler = parameterMapping.getTypeHandler();
				if (typeHandler == null) {
					throw new ExecutorException(
							"There was no TypeHandler found for parameter "
									+ propertyName + " of statement "
									+ ms.getId());
				}
				typeHandler.setParameter(ps, i + 1, value,
						parameterMapping.getJdbcType());
			}
		}
	}

	/**
	 * 生成特定数据库的分页语句
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	private String pageSql(String sql, Page page) {
		if (page == null || dialect == null || dialect.equals("")) {
			return sql;
		}

		StringBuilder sb = new StringBuilder();
		if ("hsqldb".equals(dialect)) {
			String s = sql;
			sb.append("select limit ");
			sb.append(page.getCurrentResult());
			sb.append(" ");
			sb.append(page.getSize());
			sb.append(" ");
			sb.append(s.substring(6));
		} else if ("mysql".equals(dialect)) {
			sb.append(sql);
			sb.append(" limit " + page.getCurrentResult() + ","
					+ page.getSize());
		} else if ("oracle".equals(dialect)) {
			sb.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
			sb.append(sql);
			sb.append(")  tmp_tb where ROWNUM<=");
			sb.append(page.getCurrentResult() + page.getSize());
			sb.append(") where row_id>");
			sb.append(page.getCurrentResult());
		} else {
			throw new IllegalArgumentException("分页插件不支持此数据库：" + dialect);
		}
		return sb.toString();
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
	}

	/**
	 * <p>
	 * 查询总数
	 * </p>
	 * 
	 * @param ivk
	 * @param ms
	 * @param boundSql
	 * @param parameterObject
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	private int queryTotal(Invocation ivk, MappedStatement ms, BoundSql boundSql,
			Object parameterObject, String sql) throws SQLException {
		Connection conn = (Connection) ivk.getArgs()[0];
		String countSql = "select count(0) from (" + sql + ") tmp_count";
		BoundSql bs = new BoundSql(ms.getConfiguration(), countSql,
				boundSql.getParameterMappings(), parameterObject);
        
		ResultSet rs = null;
		PreparedStatement stmt = null;

		int count = 0;
		try {
			stmt = conn.prepareStatement(countSql);
			
			BoundSql countBS = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), parameterObject);
	        Field metaParamsField = ReflectHelper.getFieldByFieldName(boundSql, "metaParameters");
	        if (metaParamsField != null) 
	        {
	            MetaObject mo = (MetaObject) ReflectHelper.getValueByFieldName(boundSql, "metaParameters");
	            ReflectHelper.setValueByFieldName(countBS, "metaParameters", mo);
	        }
	        setParameters(stmt, ms, countBS, parameterObject);
	        
			//setParameters(stmt, ms, bs, parameterObject);
			rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		}
		catch(Exception e)
		{
			throw new MyBatisSystemException(e);
		}
		finally {
			rs.close();
			stmt.close();
		}
		return count;
	}

}