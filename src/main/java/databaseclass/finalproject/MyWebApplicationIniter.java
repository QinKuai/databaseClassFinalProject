package databaseclass.finalproject;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import databaseclass.finalproject.config.RootConfig;
import databaseclass.finalproject.config.WebConfig;

public class MyWebApplicationIniter extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}
}
