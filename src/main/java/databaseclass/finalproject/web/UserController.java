package databaseclass.finalproject.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import databaseclass.finalproject.dao.IncomeRecordMapper;
import databaseclass.finalproject.dao.ResourceMapper;
import databaseclass.finalproject.dao.UserDownloadMapper;
import databaseclass.finalproject.dao.UserFollowMapper;
import databaseclass.finalproject.dao.UserMapper;
import databaseclass.finalproject.dao.UserRankMapper;
import databaseclass.finalproject.entity.Favorite;
import databaseclass.finalproject.entity.IncomeRecord;
import databaseclass.finalproject.entity.Resource;
import databaseclass.finalproject.entity.User;
import databaseclass.finalproject.entity.UserDownload;
import databaseclass.finalproject.entity.UserFollow;
import databaseclass.finalproject.entity.UserRank;
import databaseclass.finalproject.value.ResponseCode;
import databaseclass.finalproject.value.UserType;

/**
 * @author QinKuai 创建时间：2019年12月11日 描述： 用户相关数据更新API 已JSON的形式返回客户端信息
 */
@Controller
@RequestMapping(value = "/user")
@ResponseBody
@CrossOrigin
public class UserController {
	@Autowired
	private UserMapper userDao;
	@Autowired
	private ResourceMapper resourceDao;
	@Autowired
	private FavoriteMapper favoriteDao;
	@Autowired
	private UserFollowMapper followDao;
	@Autowired
	private UserDownloadMapper downloadDao;
	@Autowired
	private UserRankMapper rankDao;
	@Autowired
	private IncomeRecordMapper incomeDao;

	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

	/**
	 * 描述： 更新总的用户信息 不能通过用户更新改变的用户信息 rank,point,exp,type
	 */
	@RequestMapping(value = "/update")
	public String userUpdate(@Valid User user, Errors errors) {
		JSONObject json = new JSONObject();
		if (errors.hasErrors()) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		logger.info("User " + user.getUsername() + " try to update user's info");

		User userToUpdate = userDao.selectByUsername(user.getUsername());
		if (userToUpdate == null) {
			logger.info("User " + user.getUsername() + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		try {
			userDao.updateUser(user);
		} catch (Exception e) {
			logger.info("User " + user.getUsername() + " update user's info fail");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		logger.info("User " + user.getUsername() + " update user's info success");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	/**
	 * 描述： 在用户通过一定手段获取到积分时 只是更新用户的积分信息
	 */
	@RequestMapping(value = "/pointchange")
	public String pointUpdate(String username, int point) {
		JSONObject json = new JSONObject();

		User user = userDao.selectByUsername(username);
		if (user == null) {
			json.put(ResponseCode.KEY, ResponseCode.USER_NOT_EXIST);
			return json.toJSONString();
		}

		userDao.updatePoint(username, point);

		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	/**
	 * 描述： 在用户经验变化时 只是更新用户的经验信息
	 */
	@RequestMapping(value = "/expchange")
	public String expUpdate(String username, int exp) {
		JSONObject json = new JSONObject();

		User user = userDao.selectByUsername(username);
		if (user == null) {
			json.put(ResponseCode.KEY, ResponseCode.USER_NOT_EXIST);
			return json.toJSONString();
		}

		userDao.updateExp(username, exp);

		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	@RequestMapping(value = "/delete")
	public String deleteUser(String accessUsername, String username) {
		JSONObject json = new JSONObject();

		User manager = userDao.selectByUsername(accessUsername);
		if (manager == null || manager.getuType() != UserType.MANAGER) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		userDao.deleteByUsername(username);
		// 插入管理员日志

		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 角色更换
	// 有普通用户变为VIP
	@RequestMapping(value = "/roleupdate")
	public String roleUpdate(String username) {
		JSONObject json = new JSONObject();
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not exist");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		user.setuType(UserType.VIP);
		logger.info("user " + username + "change to VIP");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 权限提升
	// 转变为管理员
	@RequestMapping(value = "/uptomanager")
	public String upToManager(String accessName, String username) {
		JSONObject json = new JSONObject();
		User manager = userDao.selectByUsername(accessName);
		if (manager == null || manager.getuType() != UserType.MANAGER) {
			logger.info("user " + accessName + " doesn't has manager power");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		user.setuType(UserType.MANAGER);
		logger.info("user " + username + "change to Manager");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 用户的个人主页信息
	@RequestMapping(value = "/userhomepage")
	public String userHomePage(String username) {
		JSONObject json = new JSONObject();
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		// 用户信息
		// user
		json.put("user", user);
		json.put("password", user.getuPassword());

		List<UserRank> ranks = rankDao.selectAllExp();
		json.put("exps", ranks);

		// 上传资源信息
		// uploads
		List<Resource> uploads = resourceDao.selectByUsername(user.getUsername());
		logger.info("get resources uploaded by " + user.getUsername());
		json.put("uploads", uploads);

		// 获取关注数
		// follow
		int follow = followDao.selectFollowing(user.getUsername()).size();
		logger.info("get number of follower of " + user.getUsername());
		json.put("follow", follow);

		// 获取被关注数
		// followed
		int followed = followDao.selectFollower(user.getUsername()).size();
		logger.info("get number of followed of " + user.getUsername());
		json.put("followed", followed);

		logger.info("user " + username + " get UserHomePage Data");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 用户的收藏
	@RequestMapping(value = "/userfavorites")
	public String userFavorites(String username) {
		JSONObject json = new JSONObject();

		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		// 用户信息
		// user
		json.put("user", user);

		List<Favorite> favorites = favoriteDao.selectByUsername(user.getUsername());
		logger.info("get " + user.getUsername() + " favorites Info");
		List<Resource> favoriteResources = new ArrayList<Resource>();
		logger.info("get " + user.getUsername() + " favorite resources Info");
		for (Favorite favorite : favorites) {
			favoriteResources.add(resourceDao.selectByPrimaryKey(favorite.getResourceid()));
		}
		List<UserRank> ranks = rankDao.selectAllExp();
		json.put("exps", ranks);

		// 用户的收藏
		// favorites
		json.put("favorites", favoriteResources);
		logger.info("get resources favorited by " + user.getUsername());

		// 获取关注数
		int follow = followDao.selectFollowingCount(user.getUsername());
		logger.info("get number of follower of " + user.getUsername());
		// 关注数
		// follow
		json.put("follow", follow);

		// 获取被关注数
		int followed = followDao.selectFollowerCount(user.getUsername());
		logger.info("get number of followed of " + user.getUsername());
		// 粉丝数
		// follower
		json.put("followed", followed);

		logger.info("user " + username + " get FaroritePage Data");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 关注的人
	@RequestMapping(value = "/following")
	public String following(String username) {
		JSONObject json = new JSONObject();
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		json.put("user", user);

		// 获取关注数
		List<UserFollow> followings = followDao.selectFollowing(user.getUsername());
		List<User> followingUsers = new ArrayList<>();
		Map<String, List<Resource>> resourcesMap = new HashMap<>();
		Map<String, Integer> followers = new HashMap<>();
		Map<String, Integer> uploads = new HashMap<>();
		User temp = null;
		for (UserFollow follow : followings) {
			temp = userDao.selectByUsername(follow.getFollowing());
			List<Resource> resources = resourceDao.selectByUsernameWithLimit(temp.getUsername(), 3);
			resourcesMap.put(temp.getUsername(), resources);
			followers.put(temp.getUsername(), followDao.selectFollowerCount(temp.getUsername()));
			uploads.put(temp.getUsername(), resourceDao.selectUploadCount(temp.getUsername()));
			followingUsers.add(temp);
		}
		List<UserRank> ranks = rankDao.selectAllExp();
		json.put("exps", ranks);

		logger.info("get following list of " + user.getUsername());
		json.put("followings", followingUsers);

		logger.info("get following users top3 resources data");
		json.put("resources", resourcesMap);

		logger.info("user get following user's upload amount");
		json.put("uploads", uploads);

		logger.info("user get following user's follower amount");
		json.put("followers", followers);

		logger.info("user " + username + " get Following Data");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 粉丝情况
	@RequestMapping(value = "/follower")
	public String follower(String username) {
		JSONObject json = new JSONObject();
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		json.put("user", user);

		// 获取关注数
		List<UserFollow> followers = followDao.selectFollower(user.getUsername());
		List<User> followerUsers = new ArrayList<>();
		Map<String, List<Resource>> resourcesMap = new HashMap<>();
		Map<String, Integer> follower_followers = new HashMap<>();
		Map<String, Boolean> refollow = new HashMap<>();
		Map<String, Integer> uploads = new HashMap<>();
		User temp = null;
		UserFollow follow = null;
		for (UserFollow follower : followers) {
			temp = userDao.selectByUsername(follower.getFollower());
			// 粉丝的资源情况
			List<Resource> resources = resourceDao.selectByUsernameWithLimit(temp.getUsername(), 3);
			resourcesMap.put(temp.getUsername(), resources);
			// 粉丝的粉丝数
			follower_followers.put(temp.getUsername(), followDao.selectFollowerCount(temp.getUsername()));
			uploads.put(temp.getUsername(), resourceDao.selectUploadCount(temp.getUsername()));
			// 关注粉丝的情况
			follow = followDao.selectByPrimaryKey(user.getUsername(), temp.getUsername());
			refollow.put(temp.getUsername(), false);
			if (follow != null) {
				refollow.put(temp.getUsername(), true);
			}

			followerUsers.add(temp);
		}
		List<UserRank> ranks = rankDao.selectAllExp();
		json.put("exps", ranks);

		logger.info("get followers list of " + user.getUsername());
		json.put("followersinfo", followerUsers);

		logger.info("get follower users top3 resources data");
		json.put("resources", resourcesMap);

		logger.info("user get follower user's upload amount");
		json.put("uploads", uploads);

		logger.info("user get follower user's follower amount");
		json.put("followers", follower_followers);

		logger.info("user get refollow status");
		json.put("refollow", refollow);

		logger.info("user " + username + " get Followers Data");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	@RequestMapping("/download")
	@ResponseBody
	public String download(String username) {
		JSONObject json = new JSONObject();
		// 获取用户信息
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		json.put("user", user);

		List<UserDownload> downloads = downloadDao.selectDownloadByUsername(user.getUsername());
		List<Resource> resources = new ArrayList<>();
		Resource resource = null;
		for (UserDownload download : downloads) {
			resource = resourceDao.selectByPrimaryKey(download.getResourceid());
			resources.add(resource);
		}
		List<UserRank> ranks = rankDao.selectAllExp();
		json.put("exps", ranks);

		logger.info("user " + user.getUsername() + " get download resources data");
		json.put("resources", resources);

		logger.info("user " + user.getUsername() + " get downloads data");
		json.put("downloads", downloads);

		logger.info("user " + username + " get downloads Data");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	@RequestMapping("/downloaded")
	@ResponseBody
	public String downloaded(String username) {
		JSONObject json = new JSONObject();
		// 获取用户信息
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		json.put("user", user);

		List<UserDownload> downloadeds = downloadDao.selectDownloadedByUsername(user.getUsername());
		List<Resource> resources = new ArrayList<>();
		Resource resource = null;
		for (UserDownload download : downloadeds) {
			resource = resourceDao.selectByPrimaryKey(download.getResourceid());
			resources.add(resource);
		}
		List<UserRank> ranks = rankDao.selectAllExp();
		json.put("exps", ranks);

		logger.info("user " + user.getUsername() + " get downloaded resources data");
		json.put("resources", resources);

		logger.info("user " + user.getUsername() + " get downloadeds data");
		json.put("downloads", downloadeds);

		logger.info("user " + username + " get Followers Data");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	@RequestMapping("income-record")
	@ResponseBody
	public String incomeRecord(String username) {
		JSONObject json = new JSONObject();
		// 获取用户信息
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		json.put("user", user);
		
		// 用户的消费情况
		List<IncomeRecord> incomes = incomeDao.selectByUsername(user.getUsername());
		json.put("incomes", incomes);
		
		// 等级列表
		List<UserRank> ranks = rankDao.selectAllExp();
		json.put("exps", ranks);

		logger.info("user " + username + " get Incomes Data");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}
}
