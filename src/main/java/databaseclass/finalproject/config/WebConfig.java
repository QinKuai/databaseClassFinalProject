package databaseclass.finalproject.config;

import org.apache.logging.log4j.web.Log4jServletContextListener;
import org.apache.logging.log4j.web.Log4jServletFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author QinKuai
 * 创建时间：2019年12月11日
 * 描述：
 * springmvc的基本配置
 * Java代码配置
 * 等效于以前web.xml
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "databaseclass.finalproject.web")
public class WebConfig implements WebMvcConfigurer{
	
	
	/**
	 * 描述：
	 * 配置HTML的视图解析器
	 * 必定要配置的Bean
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/classes/views/");
		resolver.setSuffix(".html");
		resolver.setExposeContextBeansAsAttributes(true);
		
		return resolver;
	}
	
	
	/**
	 * 描述：
	 * 静态资源的处理
	 * 必定要配置的方法
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/**
	 * 描述：
	 * 配置静态资源的访问路径
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/static/image/**").addResourceLocations("classpath:/static/image/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	
	/**
	 * 描述：
	 * 配置校验Bean
	 * 启动Springmvc的校验机制
	 */
	@Override
	public Validator getValidator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public Log4jServletContextListener log4jServletContextListener() {
		return new Log4jServletContextListener();
	}
	
	@Bean
	public Log4jServletFilter log4jServletFilter() {
		return new Log4jServletFilter();
	}
	
}
