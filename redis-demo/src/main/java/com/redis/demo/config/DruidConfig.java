package com.redis.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DruidConfig {
	
	private final Logger logger = LoggerFactory.getLogger(DruidConfig.class);
			
	@Value("${jdbc.url}")
	  private String url;
	  	 
	  @Value("${jdbc.username}")
	  private String username;
	 
	  @Value("${jdbc.password}")
	  private String password;
	 
	  @Value("${jdbc.driverClassName}")
	  private String driverClassName;
	 
	  @Value("${jdbc.initialSize}")
	  private int initialSize;
	 
	  @Value("${jdbc.minIdle}")
	  private int minIdle;
	 
	  @Value("${jdbc.maxActive}")
	  private int maxActive;
	 
	  @Value("${jdbc.maxWait}")
	  private int maxWait;
	 
	  @Value("${jdbc.timeBetweenEvictionRunsMillis}")
	  private int timeBetweenEvictionRunsMillis;
	 
	  @Value("${jdbc.minEvictableIdleTimeMillis}")
	  private int minEvictableIdleTimeMillis;
	  
	  @Value("${jdbc.validationQuery}")
	  private String validationQuery;
	 
	  @Value("${jdbc.testWhileIdle}")
	  private boolean testWhileIdle;
	  
	  @Value("${jdbc.testOnBorrow}")
	  private boolean testOnBorrow;
	 
	  @Value("${jdbc.testOnReturn}")
	  private boolean testOnReturn;
	 
	  @Value("${jdbc.poolPreparedStatements}")
	  private boolean poolPreparedStatements;
	 
	  @Value("${jdbc.maxPoolPreparedStatementPerConnectionSize}")
	  private int maxPoolPreparedStatementPerConnectionSize;
	  
	  @Value("${jdbc.filters}")
	  private String filters;
	 
	  @Value("${jdbc.connectionProperties}")
	  private String connectionProperties;
	  
	  @Value("${jdbc.useGlobalDataSourceStat}")
	  private boolean useGlobalDataSourceStat;  
	      
	  @Value("${jdbc.druidLoginName}")
	  private String druidLoginName;  
	      
	  @Value("${jdbc.druidPassword}")
	  private String druidPassword;  
	  
	  @Value("${jdbc.druidAllow}")
	  private String druidAllow;  
	  
	  @Value("${jdbc.druidDeny}")
	  private String druidDeny;  
	  
	@Bean
	@ConditionalOnMissingBean
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driverClassName);
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		druidDataSource.setInitialSize(initialSize);
		druidDataSource.setMinIdle(minIdle);
		druidDataSource.setMaxActive(maxActive);
		druidDataSource.setMaxWait(maxWait);
		druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		druidDataSource.setValidationQuery(validationQuery);
		druidDataSource.setTestWhileIdle(testWhileIdle);
		druidDataSource.setTestOnBorrow(testOnBorrow);
		druidDataSource.setTestOnReturn(testOnReturn);
		druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		druidDataSource.setConnectionProperties(connectionProperties);  
		druidDataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
		try {
			druidDataSource.setFilters(filters);
			druidDataSource.init();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return druidDataSource;
	}

	/**
	 * 注册Servlet信息， 配置监控视图
	 *
	 * @return
	 */
	@SuppressWarnings("all")
	@Bean
	@ConditionalOnMissingBean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");
		// 白名单：
		servletRegistrationBean.addInitParameter("allow", druidAllow);
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to
		// view this page.
		servletRegistrationBean.addInitParameter("deny", druidDeny);
		// 登录查看信息的账号密码, 用于登录Druid监控后台
		servletRegistrationBean.addInitParameter("loginUsername", druidLoginName);
		servletRegistrationBean.addInitParameter("loginPassword", druidPassword);
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "true");
		return servletRegistrationBean;

	}

	/**
	 * 注册Filter信息, 监控拦截器
	 *
	 * @return
	 */
	@SuppressWarnings("all")
	@Bean
	@ConditionalOnMissingBean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
