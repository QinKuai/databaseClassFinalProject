package databaseclass.finalproject.value;

/**
 * @author QinKuai
 * 创建时间：2019年12月10日
 * 描述：
 * 用以描述前台请求后
 * 后台返回情况
 */
public class ResponseCode {
	// 响应情况的key
	public static final String KEY = "responseCode";
	// 后台响应正常
	public static final String OK = "200";
	// 用户不存在
	public static final String USER_NOT_EXIST = "201";
	// 数据库响应异常
	public static final String DATABASE_EXCEPTION = "202";
	// 用户名已存在
	public static final String USER_EXIST = "203";
	//用户邮箱已存在
	public static final String EMAIL_EXIST = "204";
	// 收藏不存在
	public static final String FAVORITE_NOT_EXIST = "205";
	// 收藏存在
	public static final String FAVORITE_EXIST = "206";
	// 后台响应错误
	// 这个标签能直接标识所有的错误和异常
	public static final String ERROR = "404";
}
