package databaseclass.finalproject.web;

import java.util.Calendar;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import databaseclass.finalproject.dao.UserMapper;
import databaseclass.finalproject.entity.User;
import databaseclass.finalproject.value.ResponseCode;
import databaseclass.finalproject.value.UserType;

@Controller
public class RegisterController {
	@Autowired
	private UserMapper userDao;
	
	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	
	
	/**
	 * 描述：
	 * 注册界面跳转
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	/**
	 * 描述：
	 * 用户注册信息校验
	 */
	@RequestMapping(value = "/register/check", method = RequestMethod.GET)
	@ResponseBody
	public String reigsterCheck(@Valid User user, Errors errors) {
		JSONObject json = new JSONObject();
		
		// 用户注册出现错误
		if (errors.hasErrors()) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		//获取用户注册时间
		user.setuRegistertime(Calendar.getInstance().getTime());
		//设置用户类型
		user.setuType(UserType.NORMAL);
		
		userDao.insert(user);
		logger.info("Register Suceess! user:{}", user);
		
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}
}
