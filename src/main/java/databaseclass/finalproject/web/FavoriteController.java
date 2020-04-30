package databaseclass.finalproject.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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

import databaseclass.finalproject.dao.FavoriteMapper;
import databaseclass.finalproject.dao.ResourceMapper;
import databaseclass.finalproject.dao.UserMapper;
import databaseclass.finalproject.entity.Favorite;
import databaseclass.finalproject.entity.Resource;
import databaseclass.finalproject.entity.User;
import databaseclass.finalproject.value.ResponseCode;

/**
 * @author QinKuai
 * 创建时间：2019年12月22日
 * 描述：用于获取用户的关注信息和粉丝信息
 */
@RequestMapping("/favorite")
@Controller
@ResponseBody
@CrossOrigin
public class FavoriteController {
	@Autowired
	private UserMapper userDao;
	@Autowired
	private FavoriteMapper favoriteDao;
	@Autowired
	private ResourceMapper resourceDao;
	
	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	
	@RequestMapping(value = "/all-favorite")
	public String favorites(String username) {
		JSONObject json = new JSONObject();
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("username " + username +" is not existed");
			json.put(ResponseCode.KEY, ResponseCode.USER_NOT_EXIST);
			return json.toJSONString();
		}
		logger.info(username + " Get All Favorites");
		List<Favorite> favorites = favoriteDao.selectByUsername(user.getUsername());
		List<Resource> resources = new ArrayList<>();
		
		Resource resource = null;
		for (Iterator<Favorite> iterator = favorites.iterator(); iterator.hasNext();) {
			Favorite favorite = iterator.next();
			resource = resourceDao.selectByPrimaryKey(favorite.getResourceid());
			if (resource == null) {
				logger.info(favorite.getUsername() + " Get All Favorites Fail");
				json.put(ResponseCode.KEY, ResponseCode.ERROR);
				return json.toJSONString();
			}
			resources.add(resource);
		}
		logger.info("Return resources list to " + username);
		json.put("resources", resources);
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/is-favorited")
	public String isFavorited(String username, Integer resourceid) {
		JSONObject json = new JSONObject();
		json.put("favorite", null);
		User user = userDao.selectByUsername(username);
		if (user == null) {
			json.put(ResponseCode.KEY, ResponseCode.USER_NOT_EXIST);
			return json.toJSONString();
		}
		logger.info(username + " Get Favorite Status To" + resourceid);
		Favorite favorite = favoriteDao.selectByPrimaryKey(username, resourceid);
		if (favorite == null) {
			logger.info("resourceid is not existed");
			json.put(ResponseCode.KEY, ResponseCode.FAVORITE_NOT_EXIST);
			return json.toJSONString();
		}
		
		json.put("favorite", favorite);
		logger.info("return favorate to " + username);
		json.put(ResponseCode.KEY, ResponseCode.FAVORITE_EXIST);
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/add-favorite")
	public String insertFavorite(@Valid Favorite favorite, Errors errors) {
		JSONObject json = new JSONObject();
		if (errors.hasErrors()) {
			logger.info("username " + favorite.getUsername() +" is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		logger.info(favorite.getUsername() + " Add A New Favorite");
		favorite.setFavoDate(Calendar.getInstance().getTime());
		try {
			favoriteDao.insert(favorite);
		} catch (Exception e) {
			logger.info(favorite.getUsername() + " Add Favorite Fail");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		logger.info(favorite.getUsername() + " Add Favorite Success");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}
	
	@RequestMapping(value = "/delete-favorite")
	public String deleteFavorite(String username, Integer resourceid) {
		JSONObject json = new JSONObject();
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info(username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		Resource resource = resourceDao.selectByPrimaryKey(resourceid);
		if (resource == null) {
			logger.info(resourceid + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		try {
			favoriteDao.deleteByPrimaryKey(username, resourceid);
		} catch (Exception e) {
			logger.info(username + " Delete Favorite Fail");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		logger.info(username + " Delete Favorite Success");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}
}
