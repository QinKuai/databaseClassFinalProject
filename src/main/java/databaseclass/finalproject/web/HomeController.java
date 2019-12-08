package databaseclass.finalproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import databaseclass.finalproject.dao.User1Mapper;
import databaseclass.finalproject.entity.User1;

@Controller
public class HomeController {
	
	@Autowired
	private User1Mapper user1Dao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root() {
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/qinkuai",method = RequestMethod.GET)
	@ResponseBody
	public String qinkuai() {
		JSONObject json = new JSONObject();
		json.put("qinkuai", "qinkuai");
		
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public String insertUser(String username, String password) {
		JSONObject json = new JSONObject();
		User1 user = new User1();
		user.setId(1);
		user.setUsername(username);
		user.setPassword(password);
		
		int i = user1Dao.insert(user);
		System.out.println(user1Dao.getClass());
		
		if (i == 1) {
			json.put("responseCode", "200");
		}else {
			json.put("responseCode", "201");
		}
		return json.toString();
	}
	
	
}
