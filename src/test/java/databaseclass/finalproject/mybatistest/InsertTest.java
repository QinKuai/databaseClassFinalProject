package databaseclass.finalproject.mybatistest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import databaseclass.finalproject.config.WebConfig;
import databaseclass.finalproject.dao.UserMapper;
import databaseclass.finalproject.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
public class InsertTest {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void insertUser() {
		String username = "Qinkuai";
		String password = "123456";
		User user = new User();
		user.setUsername(username);
		user.setuPassword(password);
		int i = userMapper.insert(user);
		System.out.println(i);
		//assertEquals(1, i);
	}
}
