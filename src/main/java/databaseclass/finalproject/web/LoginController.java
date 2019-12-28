package databaseclass.finalproject.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import databaseclass.finalproject.dao.UserMapper;
import databaseclass.finalproject.entity.User;
import databaseclass.finalproject.value.ResponseCode;

/**
 * @author QinKuai
 * 创建时间：2019年12月10日
 * 描述：
 * 负责登录页面的跳转
 * 以及登录校验
 */
@Controller
@CrossOrigin
public class LoginController {
	@Autowired
	private UserMapper userDao;
	
	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	
	/**
	 * 描述：
	 * 跳转到登录界面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	/**
	 * 描述：
	 * 校验用户的登录
	 */
	@RequestMapping(value = "login/check", method = RequestMethod.GET)
	@ResponseBody
	public String loginCheck(String username, String password) {
		JSONObject json = new JSONObject();
		
		User user = userDao.selectByUsername(username);

		if (user == null || !user.getuPassword().equals(password)) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		json.put(ResponseCode.KEY, ResponseCode.OK);
		logger.info("User Login! username:{}", user.getUsername());
		return json.toJSONString();
	}
}
