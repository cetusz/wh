package com.wh.app.web.intercepters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录验证
 * @author Benjamin
 *
 */
public class LoginIntercepter implements HandlerInterceptor {

	//private NamedThreadLocal<Long> timeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
		//long timeTotal = System.currentTimeMillis()-timeThreadLocal.get();
		//System.out.println("耗时: "+timeTotal+" ms");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		//long beginTime = System.currentTimeMillis();
		//timeThreadLocal.set(beginTime);
		if(request.getSession().getAttribute("currentUser")!=null)
			return true;
		response.sendRedirect(request.getContextPath()+"/error/error.jsp");
		return false;
	}
	
}
