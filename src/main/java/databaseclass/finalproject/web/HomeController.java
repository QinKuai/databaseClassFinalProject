package databaseclass.finalproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

/**
 * @author QinKuai
 * 创建时间：2019年12月10日
 * 描述：
 * 负责主页路径的页面跳转
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root() {
		return "index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	@RequestMapping("/home_page")
	public String homepage() {
		return "home_page";
	}
	
	@RequestMapping("/my_info")
	public String my_info() {
		return "my_info";
	}
	
	@RequestMapping("/top_up")
	public String top_up() {
		return "top_up";
	}
	
	@RequestMapping("/top_up_integral")
	public String top_up_integral(){
		return "top_up_integral";
	}
	
	@RequestMapping("collection")
	public String collection() {
		return "collection";
	}
	
	@RequestMapping("focus_on")
	public String to_audit() {
		return "to_audit";
	}
	
	@RequestMapping("/download_list")
	public String downloadList() {
		return "download_list";
	}
	
	@RequestMapping("/download_my")
	public String downloadMy() {
		return "download_my";
	}
	
}

