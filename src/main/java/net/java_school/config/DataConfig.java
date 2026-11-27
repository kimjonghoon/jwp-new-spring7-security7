package net.java_school.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Configurable
@MapperScan(basePackages = "net.java_school.mapper")
public class DataConfig {
	
	private final ApplicationContext applicationContext;
	
	public DataConfig(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Bean("dataSource")
	@Profile({"oracle", "default"})
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("java");
		dataSource.setPassword("school");
		/*
		dataSource.setMaxTotal(100);
		dataSource.setMaxWait(Duration.ofMillis(1000));
		dataSource.setPoolPreparedStatements(true);
		dataSource.setDefaultAutoCommit(true);
		dataSource.setValidationQuery("SELECT 1 FROM DUAL");
		*/
		return dataSource;
	}
	
	@Bean("dataSource")
	@Profile("mysql")
	public DataSource mysqlDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/javaskool");
		dataSource.setUsername("java");
		dataSource.setPassword("school");
		/*
		dataSource.setMaxTotal(100);
		dataSource.setMaxWait(Duration.ofMillis(1000));
		dataSource.setPoolPreparedStatements(true);
		dataSource.setDefaultAutoCommit(true);
		dataSource.setValidationQuery("SELECT 1");
		*/
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setDatabaseIdProvider(databaseIdProvider());
		factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		return factoryBean.getObject();
	}
	
	@Bean
	public DatabaseIdProvider databaseIdProvider() {
		VendorDatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
		
		Properties properties = new Properties();
		properties.setProperty("Oracle","oracle");
		properties.setProperty("MySQL","mysql");
		properties.setProperty("MariaDB", "mysql");
		databaseIdProvider.setProperties(properties);
		
		return databaseIdProvider;
	}
}