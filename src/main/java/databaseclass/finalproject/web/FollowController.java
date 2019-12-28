package databaseclass.finalproject.web;

import java.util.Calendar;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import databaseclass.finalproject.dao.UserFollowMapper;
import databaseclass.finalproject.dao.UserMapper;
import databaseclass.finalproject.entity.User;
import databaseclass.finalproject.entity.UserFollow;
import databaseclass.finalproject.value.ResponseCode;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/follow")
public class FollowController {
	@Autowired
	private UserMapper userDao;
	@Autowired
	private UserFollowMapper followDao;
	
	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	
	@RequestMapping("/is-followed")
	public String isFollowed(@Valid UserFollow userFollow, Errors errors) {
		JSONObject json = new JSONObject();
		if (errors.hasErrors()) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		User user = userDao.selectByUsername(userFollow.getFollower());
		User target = userDao.selectByUsername(userFollow.getFollowing());
		if (user == null || target == null) {
			logger.info("user is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		UserFollow follow = followDao.selectByPrimaryKey(user.getUsername(), target.getUsername());
		json.put("followed", false);
		if (follow != null) {
			json.put("followed", true);
		}
		
		logger.info("user " + user.getUsername() + " get follow status of user " + target.getUsername());
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}
	
	@RequestMapping("/cancel")
	public String cancel(@Valid UserFollow userFollow, Errors errors) {
		JSONObject json = new JSONObject();
		if (errors.hasErrors()) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		User user = userDao.selectByUsername(userFollow.getFollower());
		User target = userDao.selectByUsername(userFollow.getFollowing());
		if (user == null || target == null) {
			logger.info("user is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		try {
			followDao.deleteByPrimaryKey(user.getUsername(), target.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("fail to delete following from " + user.getUsername() + " to " + target.getUsername());
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		logger.info("user " + user.getUsername() + " cancel follow to user " + target.getUsername());
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}
	
	@RequestMapping("/follow")
	public String follow(@Valid UserFollow userFollow, Errors errors) {
		JSONObject json = new JSONObject();
		if (errors.hasErrors()) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		User user = userDao.selectByUsername(userFollow.getFollower());
		User target = userDao.selectByUsername(userFollow.getFollowing());
		if (user == null || target == null) {
			logger.info("user is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		userFollow.setFollowDate(Calendar.getInstance().getTime());
		try {
			followDao.insert(userFollow);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("fail to add following from " + user.getUsername() + " to " + target.getUsername());
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		logger.info("user " + user.getUsername() + " add follow to user " + target.getUsername());
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}
}
