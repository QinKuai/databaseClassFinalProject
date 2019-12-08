package databaseclass.finalproject.mybatistest;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import databaseclass.finalproject.configs.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class ResourceTest {
	@Value("${spring.datasource.username}")
	private String username;
	
	@Test
	public void valueTest() {
		System.out.println(username);
		//assertNotEquals("root", username);
	}
	
	@Test
	public void classPathResource() throws Exception{
		Resource resource = new ClassPathResource("application.properties");
		assertNotNull(resource);
		System.out.println(resource.isOpen());
		System.out.println(resource.exists());
		System.out.println(resource.contentLength());
//		InputStream is = resource.getInputStream();
//		byte[] bytes = new byte[1024];
//		
//		is.read(bytes);
//		System.out.println(new String(bytes));
	}
}
