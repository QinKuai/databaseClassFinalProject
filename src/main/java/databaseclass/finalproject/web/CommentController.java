package databaseclass.finalproject.web;

import java.time.LocalDateTime;

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

import databaseclass.finalproject.dao.CommentMapper;
import databaseclass.finalproject.dao.UserMapper;
import databaseclass.finalproject.entity.Comment;
import databaseclass.finalproject.entity.User;
import databaseclass.finalproject.value.ResponseCode;

@Controller
@ResponseBody
@CrossOrigin
public class CommentController {
	@Autowired
	private UserMapper userDao;
	@Autowired
	private CommentMapper commentDao;

	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

	@RequestMapping("/comment/new")
	public String newComment(@Valid Comment comment, Errors errors) {
		JSONObject json = new JSONObject();
		if (errors.hasErrors()) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		// 获取用户信息
		User user = userDao.selectByUsername(comment.getUsername());
		if (user == null) {
			logger.info("user " + comment.getUsername() + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		comment.setDate(LocalDateTime.now());
		System.out.println(comment.getDate());
		try {
			commentDao.insert(comment);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("creating a new comment fails");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	@RequestMapping("/comment/like")
	@ResponseBody
	public String likeComment(String username, Integer commentid) {
		JSONObject json = new JSONObject();
		// 获取用户信息
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("user " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		Comment comment = commentDao.selectByPrimaryKey(commentid);
		if (comment == null) {
			logger.info("comment " + commentid + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		try {
			commentDao.updateCommentLikes(comment.getCommentid());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("fail to update comment " + commentid);
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
		}

		logger.info("update comment " + commentid);
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}
}
