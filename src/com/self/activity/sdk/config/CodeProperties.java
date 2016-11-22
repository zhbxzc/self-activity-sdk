package com.self.activity.sdk.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class CodeProperties {
	private static final String PUBLIC="public.properties";
	private static final String PRIVATE="/private.properties";
	
	private static CodeProperties instance = null;
	
	Properties properties = new Properties();
	
	private CodeProperties() {
		InputStream publicPro = this.getClass().getResourceAsStream(PUBLIC);
		InputStream privatePro = this.getClass().getResourceAsStream(PRIVATE);
		try {
			properties.load(new InputStreamReader(publicPro,"UTF-8"));
		} catch (IOException e) {
			//TODO...修改为记录日志
			System.out.println("公有属性文件加载失败");
		}
		try {
			properties.load(new InputStreamReader(privatePro,"UTF-8"));
		} catch (IOException e) {
			//TODO...修改为记录日志
			System.out.println("私有属性文件加载失败");
		}
	}
	
	public static synchronized CodeProperties getInstance() {
		if (instance == null) {
			instance = new CodeProperties();
		}
		return instance;
	}
	
	public String getValue(String code){
		return getValue(code, new String[]{});
	}
	
	public String getValue(String code, String...params) {
		Object obj = properties.get(code);
		if (obj == null) {
			return null;
		}
		
		String strValue = obj.toString();
		for (int i=0; i<params.length; i++) {
			strValue = strValue.replace("{" + i + "}", params[i]);
		}
		
		return strValue;
	}
}
