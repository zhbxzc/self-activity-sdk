package com.self.activity.sdk.aop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

import com.self.activity.sdk.bean.Result;
import com.self.activity.sdk.config.CodeProperties;
import com.self.activity.sdk.exception.BusinessException;

public class BaseValidateHandler {
	public Object validate(ProceedingJoinPoint joinPoint) {
		try {
			Object[] args = joinPoint.getArgs();
			// 输入参数对象通用校验
			for (Object arg : args) {
				if (!(arg instanceof BeanPropertyBindingResult)) 
					continue;

				BeanPropertyBindingResult result = (BeanPropertyBindingResult) arg;
				if (!result.hasFieldErrors()) 
					continue;

				List<FieldError> fieldErrors = result.getFieldErrors();
				for (FieldError error : fieldErrors) {
					String codePattern = error.getDefaultMessage();
					String fieldName = error.getField();

					String code = this.fetchMsgCode(codePattern);
					String displayMsg = this.getDisplayMessage(codePattern, fieldName);

					return new Result<Object>(code, displayMsg);
				}

			}
			// 具体业务处理
			return joinPoint.proceed();
		} catch (BusinessException ex) {
			// 业务异常
			return new Result<Object>(ex.getCode(), ex.getMessage(), null, ex.getCause());
		} catch (ArrayIndexOutOfBoundsException e) {
			// 数组下标越界!
			return new Result<Object>("10102", e);
		} catch (ArithmeticException e) {
			// 数学运算异常！
			return new Result<Object>("10103", e);
		} catch (NullPointerException e) {
			// 空指针异常！
			return new Result<Object>("10104", e);
		} catch (ClassNotFoundException e) {
			// 找不到类异常
			return new Result<Object>("10105", e);
		} catch (IOException e) {
			// IO异常
			return new Result<Object>("10106", e);
		} catch (IllegalArgumentException e) {
			// 方法的参数错误
			return new Result<Object>("10107", e);
		} catch (ClassCastException e) {
			// 类型强制转换错误！
			return new Result<Object>("10108", e);
		} catch (SecurityException e) {
			// 违背安全原则异常！
			return new Result<Object>("10109", e);
		} catch (SQLException e) {
			// 操作数据库异常！
			return new Result<Object>("10110", e);
		} catch (NoSuchMethodException e) {
			// 方法未找到异常！
			return new Result<Object>("10111", e);
		} catch (InternalError e) {
			// Java虚拟机发生了内部错误！
			return new Result<Object>("10112", e);
		} catch (Throwable e) {
			// 未知的错误
			return new Result<Object>("2", e);
		}
	}

	protected String getDisplayMessage(String codePattern, String fieldName) {
		String code = fetchMsgCode(codePattern);
		String[] params = fetchMsgParams(codePattern, fieldName);
		return CodeProperties.getInstance().getValue(code, params);
	}

	private String fetchMsgCode(String codePattern) {
		if (StringUtils.isEmpty(codePattern)) {
			return null;
		}

		String[] fields = codePattern.split(",");
		return fields[0];
	}

	private String[] fetchMsgParams(String codePattern, String fieldName) {
		if (StringUtils.isEmpty(codePattern)) {
			return new String[] {};
		}

		String[] configParams = codePattern.split(",");
		String[] params = new String[configParams.length];
		params[0] = fieldName;

		if (configParams.length > 1) {
			System.arraycopy(configParams, 1, params, 1, configParams.length - 1);
		}

		return params;
	}
}