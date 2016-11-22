package com.self.activity.sdk.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.self.activity.sdk.exception.ErrorHandler;

/**
 * 
 * <p>ApplicationConfig</p>
 * <p>ApplicationConfig工具类提供了方法访问application.yml里面的配置参数。</p>
 *
 * @author		wujunhui(wujh43@taikanglife.com)
 * @version		0.0.0
 * <table style="border:1px solid gray;">
 * <tr>
 * <th width="100px">版本号</th><th width="100px">动作</th><th width="100px">修改人</th><th width="100px">修改时间</th>
 * </tr>
 * <!-- 以 Table 方式书写修改历史 -->
 * <tr>
 * <td>0.0.0</td><td>创建类</td><td>zsc</td><td>2016年10月28日 下午3:46:00</td>
 * </tr>
 * <tr>
 * <td>XXX</td><td>XXX</td><td>XXX</td><td>XXX</td>
 * </tr>
 * </table>
 */
public class ApplicationConfig {
	//应答中是否包含源异常堆栈信息
	private static String sdk_result_hasThrowable = "sdk.result.hasThrowable";
	
	private static ApplicationConfig instance = null;
	
	Properties properties=new Properties();
	
	public static synchronized ApplicationConfig getInstance() {
		if (instance == null) {
			instance = new ApplicationConfig();
		}
		return instance;
	}
	
	private ApplicationConfig() {
		try {
			InputStream publicPro = ApplicationConfig.class.getResourceAsStream("/application.yml");
			properties.load(new InputStreamReader(publicPro,"UTF-8"));
		} catch (IOException e) {
			ErrorHandler.reportError("10201", e);
		}
	}
	
	/**
	 * 返回配置参数值
	 */
	public String getValue(String key, String defVal) {
		return properties.getProperty(key, defVal);
	}
	
	/**
	 * 返回配置参数值
	 * @return
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * 判断REST接口返回数据对象中是否包含源异常堆栈信息.
	 * @return
	 */
	public boolean hasThrowable() {
		String hasThrowable = properties.getProperty(ApplicationConfig.sdk_result_hasThrowable, "true");
		return "true".equalsIgnoreCase(hasThrowable);
	}
}
