package databaseclass.finalproject.config;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author QinKuai
 * 创建时间：2019年12月11日
 * 描述：
 * 数据库配置信息
 * 同时满足Mybatis的Mapper映射关系
 */
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
	
	/**
	 * 描述：
	 * 配置数据源
	 */
	@Bean
	public DataSource dataSource() {
		return new PooledDataSource(datasource_driver, datasource_url, datasource_username, datasource_password);
	}
	
	/**
	 * 描述：
	 * 配置Mybatis的数据源和Mapper映射路径
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		//通过XML配置Mapper时使用
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:/mapper/*Mapper.xml"));
		return sessionFactory.getObject();
	}
}
