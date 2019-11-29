package com.dushan.zhongchou.mvc.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dushan.zhongchou.constant.ZhongChouConstant;
import com.dushan.zhongchou.entity.Admin;
import com.dushan.zhongchou.exception.AccessDeniedException;
import com.dushan.zhongchou.util.AjaxUtil;
import com.dushan.zhongchou.util.ResultEntity;
import com.google.gson.Gson;

public class LoginIntercepter extends HandlerInterceptorAdapter{
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	HttpSession session=request.getSession();
	Admin admin=(Admin) session.getAttribute("loginAdmin");
	if(admin !=null) {
		return true;
	}
	// 5.代码如果执行到这里，就说明admin为null，没有登录。
			// ①判断当前请求是否是Ajax请求
			boolean checkResult = AjaxUtil.checkRequestType(request);
			
			if(checkResult) {
				
				// 将异常信息封装到JSON对象中返回即可
				// ②创建ResultEntity对象封装异常信息
				ResultEntity<Object> resultEntity = ResultEntity.failed(ZhongChouConstant.MESSAGE_ACCESS_DENIED);
				
				// ③将ResultEntity对象转换为JSON
				Gson gson = new Gson();
				String json = gson.toJson(resultEntity);
				
				// ④将json数据作为响应体返回给浏览器
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write(json);
				
				// ⑤我们自己使用JSON数据返回了响应，不放行
				return false;
			}
	throw new AccessDeniedException(ZhongChouConstant.MESSAGE_ACCESS_DENIED);
}
}
