package databaseclass.finalproject.config;

public class AlipayConfig {
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101500691718";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCS97TjRXuXbK1oMOyhUcJD0DaWU/6UQsGMdh9xV6xkZhPIcogCQBbH/lf9YpNDVhlkeVE4Qe8pYvhc/YmOrqghK89Ehib/awolERpejp1LavwGPJ+usMcr9hq9g4NO1fJMn27aQoSauRlxD9ySinZo0+TLyh7N2fnNMvDjHH0TOqT3SthOmF6Z/twUXp6H9kk6LLS1iBwQ0rfITCU6R/zytG2HMcahQ2eZwq4OjQx1QTKSpUuFhqvOJfzzz5iK/k2QCvb5WzrjuMw2bli32S/1A6voQDcDMuK93DsGfC3E90oyi0Vy4bhFW9jRSaRwdgyBucmb4FmJixfUZ2mTqFEjAgMBAAECggEAJXxg+wch+wGRSV4RPSRpdHqLMdTCvnY4f+cxJevC4OjYauwrnMdUCDbroomeHjK7z1DSUpMeuWOxDabtiyMpZyyRdVzyXXuj3sh5/VxK6o2jMsQLxrb4VlIzh9sfhuAzKTARSkJlKEHGJ3xwOkBu61YAqaooKUFL4fb4UjVWAa00qssz3jytkmxPnWbDB8/yS5UDu7woWfrPE9KgzNoXtUAk3WKvQ5vDEdKXBe7usHC7NKoQOXFerDAqgEDm9BkmX621pRcQIJJviMYXVPe4sx4gABgtK4j1+U2t0kfGhR1ZnYIXX+GndaKQoaAYoZY1dAYOSi0mlmYRbgUJizGZYQKBgQDDbju/eizFUoWTDh0n2rTDnYjcsv5cbScJgQPrLF/DgjW23R22qtzpLI6W4+ZH7GXBvY0ti7wsaLQjdQTw68t8W7HjggpzzR6K0DtrAJC9Lj5TW6on7NLzjNekIpcJ+nu4bPnBeKT8GN6LNhfq5BgHmTMsihprMCPJDV4EnjJhfwKBgQDAhFgTmcmSjfQjkbSP4D0j6Viwm+503vtcs+3Mp3/kbCXbJq93NaJdI4uOD8pJyCVyV62iysf7+PkwqPJcz0j0eg8d1lEgaLYQte7OrBZQLt4L8RvjyC0RCGijYzo3QfBovvT8cW5NrIzjr+N2gWtzFBaY5njLcE3jVBPgVIwaXQKBgBa6jUxZozdXv504uP9Xmkpd2fsA/kA1maKrVI9XW9PbIvAfi0MhYAvmWLcHzlJzFeqQZwjzBXGMw9aT131FjybpayjwiNKq+nBeg2qQaET4Sdtb/9Ag5O/aY4H9W9+XOChc1DdCFr4TDl7WbbWtzXWkxaTacvQgZykM2vBija11AoGAEwnDcATIU07octAI7CV/xlpgI+73sOUQNIWyoYd69Urgn4B+36oVKWLHfUAAgDVjxp/3Et880aU4d2Btv1HcZBjh/9Be63aPcq2bNSL9feZ0nPe2C1uTYVjUiDva2PYTYThpH8HT56iFskqFkwWPa6zz4yD/lxa/DRkWchvKUx0CgYAc0k6nvme9E7hsZCSdO7Mpz0/LDvLG2yJR876qsOKxx0ktU7+jepN3a/YsHrbVgQsLUhqV0MKw0zXQTHxti3Z77Rh7Fw/xmy29rN+qoAQ23cms5EZUKmZNXbKXp5WOrj5aVtEHQRkT3x1Q8oViRRQV0f+BegtaC6I0tCtG/UgojQ==";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqMIRT+lWyCT+TEwGE1jippjviSjJaLkfHziSboz4OGzLg9VkbVEITHpFB/ivL9KNrmqk955ta6pJL407poMyIxrSYhRJQUcbwNtU/vU8ej0uUkFQngJtATSpyZ1zTRBB6Rn4VcWMhx6HDMW83Er+E7dSMqVLbkqMCQqsZgIDdGsc6eZV5FYYc1dO5oLQBWka0lnRN60Js4+Oi0P8BIbpI/I7QrmAblC+FyX2GGiuE2KByEGGCwo0TP89cHUjyd1pyR+JHIPdS2Ve5IHgpOiAoMEk+08YGQbaPOvCmlMWX2qGfjDQlVpUXUKyusI2Seh7a2w+E8OdYTkJjK1TWxhIdQIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// public static String notify_url = "http:/localhost:8080/finalproject/forum";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/alipay/alipayreturn";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	// public static String log_path = "C:\\";
}
