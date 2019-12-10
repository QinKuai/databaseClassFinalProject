package databaseclass.finalproject.web;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

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
public class LoginController {
	
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
	@RequestMapping(value = "/login/check", method = RequestMethod.POST)
	@ResponseBody
	public String loginCheck(@Valid User user, Errors errors) {
		JSONObject json = new JSONObject();
		
		
		if (errors.hasErrors()) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			System.out.println("ERROR");
		}else {
			json.put(ResponseCode.KEY, ResponseCode.OK);
		}
		logger.info(json);
		return json.toJSONString();
	}
}
