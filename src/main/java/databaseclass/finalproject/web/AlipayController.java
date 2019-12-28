package databaseclass.finalproject.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import databaseclass.finalproject.config.AlipayConfig;
import databaseclass.finalproject.dao.UserMapper;
import databaseclass.finalproject.entity.User;

@RequestMapping("/alipay")
@Controller

public class AlipayController {
	@Autowired
	private UserMapper userDao;
	
	private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	
	@RequestMapping("/goAlipay")
	@ResponseBody
	public String goAlipay(String username, HttpServletRequest request, HttpServletResponse response) throws Exception{
		User user = userDao.selectByUsername(username);
		
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		
		String outTradeNo = "6823789339978248";
		String totalAmount = "0.01";
		String subject = "Test";
		String body = "Test body";
		
		String timeout = "1c";
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
				+ "\"total_amount\":\""+ totalAmount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"timeout_express\":\""+ timeout +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		
		return result;
	}
	
//	@RequestMapping("/return")
//	@ResponseBody
//	public String returnResult(HttpServletRequest request, HttpServletRequest response) {
//		
//	}
}
