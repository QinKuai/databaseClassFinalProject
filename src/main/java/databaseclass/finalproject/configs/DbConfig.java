package databaseclass.finalproject.configs;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"databaseclass.finalproject.dao"})
public class DbConfig {
	@Value("${spring.datasource.username}")
	private String datasource_username;
	
	@Value("${spring.datasource.password}")
	private String datasource_password;
	
	@Value("${spring.datasource.url}")
	private String datasource_url;
	
	@Value("${spring.datasource.driver-class-name}")
	private String datasource_driver;
	
	@Bean
	public DataSource dataSource() {
		return new PooledDataSource(datasource_driver, datasource_url, datasource_username, datasource_password);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:/mapper/*Mapper.xml"));
		return sessionFactory.getObject();
	}
}
