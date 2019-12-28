package databaseclass.finalproject.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import databaseclass.finalproject.dao.CommentMapper;
import databaseclass.finalproject.dao.PostMapper;
import databaseclass.finalproject.dao.UserFollowMapper;
import databaseclass.finalproject.dao.UserMapper;
import databaseclass.finalproject.entity.Comment;
import databaseclass.finalproject.entity.Post;
import databaseclass.finalproject.entity.User;
import databaseclass.finalproject.entity.UserFollow;
import databaseclass.finalproject.value.ResourceType;
import databaseclass.finalproject.value.ResponseCode;

@Controller
@CrossOrigin
public class PostController {
	@Autowired
	private UserMapper userDao;
	@Autowired
	private PostMapper postDao;
	@Autowired
	private CommentMapper commentDao;
	@Autowired
	private UserFollowMapper followDao;

	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

	@RequestMapping("/forum")
	public String forum() {
		return "forum_index";
	}

	@RequestMapping("/forum/homepage")
	@ResponseBody
	public String homePage(String username, String posttype, String ranktype) {
		JSONObject json = new JSONObject();
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		List<Post> posts = null;

		if (posttype.equals("all")) {
			switch (ranktype) {
			case "like":
				posts = postDao.selectHomePageAllByLike();
				break;
			case "time":
				posts = postDao.selectHomePageAllByTime();
				break;
			default:
				logger.info("ranktype " + ranktype + " is not existed");
				json.put(ResponseCode.KEY, ResponseCode.ERROR);
				return json.toJSONString();
			}
		} else {
			Integer fieldid = ResourceType.getType(posttype);
			if (fieldid == null) {
				logger.info("field " + posttype + " is not existed");
				json.put(ResponseCode.KEY, ResponseCode.ERROR);
				return json.toJSONString();
			}
			switch (ranktype) {
			case "like":
				posts = postDao.selectHomePageByLike(fieldid);
				break;
			case "time":
				posts = postDao.selectHomePageByTime(fieldid);
				break;
			default:
				logger.info("ranktype " + ranktype + " is not existed");
				json.put(ResponseCode.KEY, ResponseCode.ERROR);
				return json.toJSONString();
			}
		}

		json.put("posts", posts);
		logger.info("user " + username + " get homepage data in type " + posttype + " by " + ranktype);

		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	@RequestMapping("/forum/ranklist")
	@ResponseBody
	public String rankList(String username, String ranktype) {
		JSONObject json = new JSONObject();
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		List<Post> posts = null;
		switch (ranktype) {
		case "like":
			posts = postDao.selectTop10ByLike();
			break;
		case "time":
			posts = postDao.selectTop10ByTime();
			break;
		default:
			logger.info("ranktype " + ranktype + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		logger.info("user " + username + " get ranklist by " + ranktype);
		json.put("ranklist", posts);

		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	@RequestMapping("/posts/{postid}")
	public ModelAndView getPost(@PathVariable String postid) {
		return new ModelAndView("forward:/posts");
	}

	@RequestMapping("/posts")
	public String post() {
		return "forum_detail";
	}

	// 获取到一个帖子的所有信息
	// 包含评论信息
	@RequestMapping("/post/data")
	@ResponseBody
	public String postData(String username, Integer postid) {
		JSONObject json = new JSONObject();
		// 获取username对应的用户信息
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		// 获取postid对应的帖子信息
		Post post = postDao.selectByPrimaryKey(postid);
		if (post == null) {
			logger.info("postid " + postid + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		// 获取用户对帖子发起者的关注情况
		UserFollow follow = followDao.selectByPrimaryKey(user.getUsername(), post.getUsername());
		boolean followed = false;
		if (follow != null) {
			followed = true;
		}
		json.put("follow", followed);

		logger.info("user " + username + " get data of post " + postid);
		json.put("post", post);

		// 获取评论的信息
		List<Comment> comments = commentDao.selectAllByPostid(post.getPostid());
		json.put("comments", comments);

		// 获取评论的用户信息
		List<User> users = new ArrayList<>();
		;
		for (Comment comment : comments) {
			users.add(userDao.selectByUsername(comment.getUsername()));
		}
		json.put("users", users);
		logger.info("user " + username + " get comments of post " + postid);

		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	@RequestMapping("/newpost")
	public String newPost() {
		return "forum_add";
	}
	
	
	@RequestMapping("/post/new")
	@ResponseBody
	public String newPost(@Valid Post post, Errors errors) {
		JSONObject json = new JSONObject();
		if (errors.hasErrors()) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		// 获取
		User user = userDao.selectByUsername(post.getUsername());
		if (user == null) {
			logger.info("user " + post.getUsername() + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		post.setCreatedate(Calendar.getInstance().getTime());
		try {
			postDao.insert(post);
			post.setPostid(postDao.getLastInsertID());
		} catch (Exception e) {
			logger.info("insert post failed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		json.put("postid", post.getPostid());

		logger.info("insert a new post");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	@RequestMapping("post/delete")
	@ResponseBody
	public String deletePost(String username, Integer postid) {
		JSONObject json = new JSONObject();
		// 获取到用户
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		Post post = postDao.selectByPrimaryKey(postid);
		if (post == null || !post.getUsername().equals(user.getUsername())) {
			logger.info("post " + postid + "is not existed or user " + username + " doesn't own post " + postid);
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		try {
			postDao.deleteByPrimaryKey(postid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("delete post " + postid + " error");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		logger.info("user " + user.getUsername() + " delete post " + postid);
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	@RequestMapping("/post/mypost")
	@ResponseBody
	public String myPost(String username) {
		JSONObject json = new JSONObject();

		// 获取用户信息
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		List<Post> posts = postDao.selectPostByUsername(user.getUsername());
		logger.info("user " + username + " get all own post data");
		json.put("posts", posts);

		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	@RequestMapping("/post/like")
	@ResponseBody
	public String like(String username, Integer postid) {
		JSONObject json = new JSONObject();

		// 获取用户信息
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		Post post = postDao.selectByPrimaryKey(postid);
		if (post == null) {
			logger.info("post " + postid + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		try {
			postDao.updatePostLike(postid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("update like of " +  postid + " error");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		logger.info("user " + user.getUsername() + " like post " + postid);
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}
	
	@RequestMapping("myposts")
	public String myPosts() {
		return "user_post_index";
	}

}
