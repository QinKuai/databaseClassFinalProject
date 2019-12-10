package databaseclass.finalproject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author QinKuai
 * 创建时间：2019年12月11日
 * 描述：
 * 除去Web配置之外的Spring其他配置信息
 */
@Configuration
@ComponentScan(excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
})
@PropertySource("classpath:application.properties")
@Import(DbConfig.class)
public class RootConfig {
	
}
