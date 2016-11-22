package com.self.activity.sdk.exception;

import com.self.activity.sdk.config.CodeProperties;

public class ErrorHandler {

	public static void reportError(String code) {
		reportError(code, new String[]{});
	}
	
	public static void reportError(String code, String...params) {
		String msg = CodeProperties.getInstance().getValue(code, params);
		reportError(code, msg, null);
	}
	
	public static void reportError(String code, Throwable cause) {
		String msg = CodeProperties.getInstance().getValue(code);
		reportError(code, msg, cause);
	}
	
	private static void reportError(String code, String msg, Throwable cause) {
		throw new BusinessException(code, msg, cause);
	}
}
