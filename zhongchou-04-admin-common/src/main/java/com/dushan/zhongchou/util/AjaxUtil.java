package com.dushan.zhongchou.util;

import javax.servlet.http.HttpServletRequest;

public class AjaxUtil {
	
	/**
	 * 判断当前请求是否为Ajax请求
	 * @param request
	 * @return
	 * 		true : 是Ajax请求
	 *      false：非Ajax请求
	 */
	public static boolean checkRequestType(HttpServletRequest request) {
		
		// 获取代表Ajax请求特征的请求消息头
		String accept = request.getHeader("Accept");
		
		String xRequested = request.getHeader("X-Requested-With");
		
		// 判断是否具备相应特征
		if(accept != null && accept.length() > 0 && accept.contains("application/json")) {
			return true;
		}
		
		if(xRequested != null && xRequested.length() > 0 && xRequested.contains("XMLHttpRequest")) {
			return true;
		}
		
		return false;
	}

}
