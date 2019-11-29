package com.dushan.zhongchou.mvc.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.dushan.zhongchou.constant.ZhongChouConstant;
import com.dushan.zhongchou.exception.LoginAcctAlreadyInUseExceptionForUpdate;
import com.dushan.zhongchou.util.AjaxUtil;
import com.dushan.zhongchou.util.ResultEntity;
import com.google.gson.Gson;

@ControllerAdvice
public class CrowdExceptionResolver {
	
	private Map<String, String> exceptionMapping;
	
	{
		exceptionMapping = new HashMap<>();
		
		// 设置异常类型和要返回的视图的对应关系
		exceptionMapping.put("java.lang.RuntimeException", "system-error");
		exceptionMapping.put("java.lang.ArithmeticException", "system-error");
		exceptionMapping.put("com.dushan.zhongchou.exception.LoginException", "admin-login");
		exceptionMapping.put("com.dushan.zhongchou.exception.AccessDeniedException", "admin-login");
		exceptionMapping.put("com.dushan.zhongchou.exception.LoginAcctAlreadyInUseException", "admin-add");
		exceptionMapping.put("com.dushan.zhongchou.exception.LoginAcctAlreadyInUseExceptionForUpdate","system-error");
	}
	
	@ExceptionHandler(value=Exception.class)
	public ModelAndView resolveException(Exception exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 判断当前请求是否为Ajax请求
		boolean checkResult = AjaxUtil.checkRequestType(request);
		
		if(checkResult) {
			
			// 将异常信息封装到JSON对象中返回即�?
			// 1.创建ResultEntity对象封装异常信息
			ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
			
			// 2.将ResultEntity对象转换为JSON
			Gson gson = new Gson();
			String json = gson.toJson(resultEntity);
			
			// 3.将json数据作为响应体返回给浏览�?
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(json);
			
			// 4.我们自己使用JSON数据返回了响应，不让SpringMVC再返回ModelAndView
			return null;
		}
		
		// 根据具体的异常类型调用对应的方法进行定制
		// 1.获取当前异常类型的全类名
		String name = exception.getClass().getName();
		
		// 2.从Map中获取对应的视图名称
		String errorViewName = exceptionMapping.get(name);
		
		// 3.将异常对象和视图名称封装到ModelAndView�?
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName(errorViewName);
		modelAndView.addObject(ZhongChouConstant.ATTR_NAME_EXCEPTION, exception);
		
		// 4.返回ModelAndView对象带着异常对象和视图名�?
		return modelAndView;
		
	}

}
