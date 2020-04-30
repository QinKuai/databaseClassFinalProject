package databaseclass.finalproject.web;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import databaseclass.finalproject.dao.FieldMapper;
import databaseclass.finalproject.dao.ResourceMapper;
import databaseclass.finalproject.dao.UserDownloadMapper;
import databaseclass.finalproject.dao.UserMapper;
import databaseclass.finalproject.entity.Resource;
import databaseclass.finalproject.entity.User;
import databaseclass.finalproject.entity.UserDownload;
import databaseclass.finalproject.value.ResourceType;
import databaseclass.finalproject.value.ResponseCode;
import databaseclass.finalproject.value.UserType;

@Controller
@CrossOrigin
public class ResourceController {
	@Autowired
	private ResourceMapper resourceDao;
	@Autowired
	private UserMapper userDao;
	@Autowired
	private FieldMapper fieldDao;
	@Autowired
	private UserDownloadMapper downloadDao;

	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

	// 访问具体资源时将资源ID
	// 存入到网址中
	// ready
	@RequestMapping(value = "/resource/{resourceid}", method = RequestMethod.GET)
	public ModelAndView resourcePlusID(@PathVariable String resourceid) {
		return new ModelAndView("forward:/resource");
	}

	// 为资源详情提供界面
	// ready
	@RequestMapping(value = "/resource")
	private String resource() {
		return "new_list_detail";
	}

	// 获取资源详情的数据
	// ready
	@RequestMapping(value = "/resource/data")
	@ResponseBody
	public String getResource(Integer resourceid) {
		JSONObject json = new JSONObject();
		Resource resource = resourceDao.selectByPrimaryKey(resourceid);
		if (resource == null) {
			logger.info("resourceid is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		json.put("resource", resource);

		logger.info("Success Return resource data of " + resourceid);
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 主页需要获取的资源信息
	// ready
	@RequestMapping(value = "/resource/homepage")
	@ResponseBody
	public String homePageData() {
		JSONObject json = new JSONObject();

		logger.info("homepage data load");
		List<Resource> games = resourceDao.selectByOrder(ResourceType.GAME, 4);
		if (games.size() != 4) {
			logger.info("data error");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		List<Resource> movies = resourceDao.selectByOrder(ResourceType.MOVIE, 4);
		if (movies.size() != 4) {
			logger.info("data error");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		List<Resource> others = resourceDao.selectByOrder(ResourceType.OTHER, 4);
		if (others.size() != 4) {
			logger.info("data error");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		System.out.println(games.get(2));

		json.put("games", games);
		json.put("movies", movies);
		json.put("others", others);

		logger.info("homepage data send");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 上传资源
	@RequestMapping(value = "/resource/add", method = RequestMethod.POST)
	@ResponseBody
	public String addResource(@Valid Resource resource, Errors errors, 
			@RequestParam("picture") MultipartFile file, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		if (errors.hasErrors()) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		// 用户信息
		User user = userDao.selectByUsername(resource.getUsername());
		if (user == null) {
			logger.info("User " + resource.getUsername() + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		if (!file.isEmpty()) {
			int fileNumber = resourceDao.selectAmount() + 1;
			String picaddr = "static/image/png/" + fileNumber + file.getOriginalFilename();
			String path = "F:/tomcat9/webapps/finalproject/WEB-INF/classes/" + picaddr;
			logger.info("upload picture to this path: " + path);
			try {
				file.transferTo(new File(path));
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("Upload " + file.getOriginalFilename() + " failed");
				json.put(ResponseCode.KEY, ResponseCode.ERROR);
				return json.toJSONString();
			}
			
			resource.setPicaddr(picaddr);
		}else {
			resource.setPicaddr("static/png/default.png");
		}
		

		resource.setrTime(LocalDateTime.now());
		try {
			resourceDao.insert(resource);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("insert resource fail");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		logger.info("user " + user.getUsername() + " insert resource success");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 删除资源
	@RequestMapping(value = "/resource/delete")
	@ResponseBody
	public String deleteResource(String username, Integer resourceid) {
		JSONObject json = new JSONObject();
		// 获取用户信息
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("User " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		// 获取资源信息
		Resource resource = resourceDao.selectByPrimaryKey(resourceid);
		if (resource == null) {
			logger.info("Resource " + resourceid + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		if (!resource.getUsername().equals(user.getUsername()) && !user.getuType().equals(UserType.MANAGER)) {
			logger.info("user " + username + " has no access to this resource");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		try {
			resourceDao.deleteByPrimaryKey(resourceid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("delete resource fail");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		logger.info("user " + username + " delete resource " + resourceid + " success");
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 更新资源
	@RequestMapping(value = "/resource/update")
	@ResponseBody
	public String updateResource(Integer resourceid, @Valid Resource resource, Errors errors) {
		JSONObject json = new JSONObject();
		if (errors.hasErrors()) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		// 获取用户信息
		User user = userDao.selectByUsername(resource.getUsername());
		if (user == null) {
			logger.info("User " + resource.getUsername() + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		// 获取资源信息
		Resource targetResource = resourceDao.selectByPrimaryKey(resourceid);
		if (targetResource == null) {
			logger.info("Resource " + resourceid + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		// 更新资源信息
		targetResource.setContent(resource.getContent());
		//targetResource.setPicaddr();
		targetResource.setRestype(resource.getRestype());
		targetResource.setTitle(resource.getTitle());
		targetResource.setUrl(resource.getUrl());
		
		try {
			resourceDao.updateByPrimaryKey(targetResource);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("resource update error");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 核对资源是否已被下载过
	@RequestMapping("/resource/downloadcheck")
	@ResponseBody
	public String downloadCheck(String username, Integer resourceid) {
		JSONObject json = new JSONObject();
		// 获取用户信息
		User user = userDao.selectByUsername(username);
		if (user == null) {
			logger.info("User " + username + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		// 获取资源信息
		Resource resource = resourceDao.selectByPrimaryKey(resourceid);
		if (resource == null) {
			logger.info("Resource " + resourceid + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		json.put("owner", false);
		json.put("downloaded", false);
		// 确定这个资源的所有者
		if (resource.getUsername().equals(user.getUsername())) {
			logger.info("user " + username + " is the owner of resource " + resourceid);
			json.put("owner", true);
			json.put(ResponseCode.KEY, ResponseCode.OK);
			return json.toJSONString();
		}

		// 确定这个资源已经被下载过了
		UserDownload download = downloadDao.selectByUsernameAndResourceid(username, resourceid);
		if (download != null) {
			logger.info("user " + username + " has downloaded resource " + resourceid);
			json.put("downloaded", true);
			json.put(ResponseCode.KEY, ResponseCode.OK);
			return json.toJSONString();
		}

		logger.info("user " + user.getUsername() + " check resource from resourceid " + resource.getResourceid());
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 下载资源
	@RequestMapping("/resource/download")
	@ResponseBody
	public String downloadResource(@Valid UserDownload userDownload, Errors errors) {
		JSONObject json = new JSONObject();
		System.out.println(errors);
		if (errors.hasErrors()) {
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		// 获取用户信息
		User user = userDao.selectByUsername(userDownload.getUsername());
		if (user == null) {
			logger.info("User " + userDownload.getUsername() + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		System.out.println(user);
		
		// 获取资源信息
		Resource resource = resourceDao.selectByPrimaryKey(userDownload.getResourceid());
		if (resource == null) {
			logger.info("Resource " + userDownload.getResourceid() + " is not existed");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}
		
		JSONObject check = JSON.parseObject(downloadCheck(user.getUsername(), resource.getResourceid()));
		if (check.getBooleanValue("owner") || check.getBooleanValue("downloaded")) {
			logger.info("Download check fail");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		// 设置下载时间
		userDownload.setdDate(Calendar.getInstance().getTime());
		// 设置资源所有者
		userDownload.setUserUpload(resource.getUsername());
		// 设置扣除积分数
		int point = user.getuType().equals(UserType.NORMAL) ? resource.getPoint() : resource.getPoint() * 4 / 5;
		userDownload.setdPoint(point);
		System.out.println(point);
		// 插入下载记录表中
		System.out.println(userDownload);
		try {
			downloadDao.insert(userDownload);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("data error");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		logger.info("user " + user.getUsername() + " get resource from resourceid " + resource.getResourceid());
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}

	// 某一类资源的路由
	// 将这类资源标签的信息存在路由中
	// ready
	@RequestMapping(value = "/resources/{typename}")
	public ModelAndView resourcesInType(@PathVariable String typename) {
		return new ModelAndView("forward:/resources");
	}

	// 为某一类资源提供页面
	// ready
	@RequestMapping(value = "/resources")
	public String detailResource() {
		return "new_list";
	}

	// 获取某类资源的数据信息
	// ready
	@RequestMapping(value = "/resources/detail")
	@ResponseBody
	public String getResourcesByType(String typename) {
		JSONObject json = new JSONObject();
		Integer resourceType = ResourceType.getType(typename);
		if (resourceType == null) {
			logger.info(typename + " is not exists");
			json.put(ResponseCode.KEY, ResponseCode.ERROR);
			return json.toJSONString();
		}

		Integer amount = fieldDao.selectAmountByFieldid(resourceType);
		json.put("amount", amount);

		List<Resource> resources = resourceDao.selectByType(resourceType);
		json.put("resources", resources);

		logger.info("return resources data in type " + typename);
		json.put(ResponseCode.KEY, ResponseCode.OK);
		return json.toJSONString();
	}
	
	@RequestMapping("/upload")
	public String upload() {
		return "uploading";
	}
}
