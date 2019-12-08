package databaseclass.finalproject.mybatistest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import databaseclass.finalproject.configs.WebConfig;
import databaseclass.finalproject.dao.User1Mapper;
import databaseclass.finalproject.entity.User1;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
public class InsertTest {
	@Autowired
	private User1Mapper user1Mapper;
	
	@Test
	public void insertUser() {
		String username = "Qinkuai";
		String password = "123456";
		User1 user = new User1();
		user.setId(1);
		user.setUsername(username);
		user.setPassword(password);
		int i = user1Mapper.insert(user);
		System.out.println(i);
		//assertEquals(1, i);
	}
}
