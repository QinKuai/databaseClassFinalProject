package databaseclass.finalproject.config;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.logging.log4j.web.Log4jServletContextListener;
import org.apache.logging.log4j.web.Log4jServletFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
		resolver.setPrefix("WEB-INF/classes/view/");
		resolver.setSuffix(".html");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Bean(name = "multipartResolver")
	public MultipartResolver multiPartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(4 * 1024 * 1024);
		resolver.setDefaultEncoding("utf-8");
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
	 * 解决返回的JSON中文乱码问题
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter httpMessageConverter = new StringHttpMessageConverter(Charset.forName("utf-8"));
		converters.add(httpMessageConverter);
		//WebMvcConfigurer.super.configureMessageConverters(converters);
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
		registry.addResourceHandler("/static/luntanres/**").addResourceLocations("classpath:/static/luntanres/");
		registry.addResourceHandler("/static/images/**").addResourceLocations("classpath:/static/images/");
		//WebMvcConfigurer.super.addResourceHandlers(registry);
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
