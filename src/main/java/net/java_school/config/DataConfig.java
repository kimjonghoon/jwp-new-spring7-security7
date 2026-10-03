package net.java_school.config;

import java.time.Duration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Configurable
@MapperScan(basePackages = "net.java_school.mapper")
public class DataConfig {
	
	private final ApplicationContext applicationContext;
	
	public DataConfig(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("java");
		dataSource.setPassword("school");
		
		dataSource.setMaxTotal(100);
		dataSource.setMaxWait(Duration.ofMillis(1000));
		dataSource.setPoolPreparedStatements(true);
		dataSource.setDefaultAutoCommit(true);
		dataSource.setValidationQuery("SELECT 1 FROM DUAL");
		
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setConfigLocation(applicationContext.getResource("classpath:net/java_school/mybatis/Configuration.xml"));
		return factoryBean.getObject();
	}
	
}
