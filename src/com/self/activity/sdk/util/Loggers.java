package com.self.activity.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public class Loggers {
	private static final Logger logger=LoggerFactory.getLogger(Loggers.class);
	public static void info(HttpHeaders headers,boolean isRequest,String bizCode,String serviceCode,String meassage){
		String info;
		if(isRequest){
			info="REQUEST";
		}else{
			info="RESPONSE";
		}
		logger.info(
				"["+info+"]["+bizCode+"]["+
				headers.getFirst("TK_BUSINESS_SERIALID")+"]["+
				headers.getFirst("TK_REQUEST_SYS_CODE")+"]["+
				headers.getFirst("TK_REQUEST_MODULE_CODE")+"]["+
				headers.getFirst("TK_REQUEST_NODE_IP")+"]["+
				serviceCode+"]["+meassage+"]");
	}
}
