package com.ebeijia.contro.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ebeijia.contro.exception.SessionTimeoutException;
import com.ebeijia.entity.TblAdminInf;

/**
 * @author zhicheng.xu
 * @date 2015年4月22日
 */
public class SessionInterceptor implements HandlerInterceptor {

	public String[] allowUrls;// 还没发现可以直接配置不拦截的资源，所以在代码里面来排除

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		String requestUrl = request.getRequestURI().replace(
				request.getContextPath(), "");
//		System.out.println("========requestUrl:"+requestUrl);
		if (null != allowUrls && allowUrls.length >= 1)
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}

		TblAdminInf tblAdminInf = (TblAdminInf) request.getSession().getAttribute("operator");
//		System.out.println("========tblAdminInf:"+tblAdminInf);
		if (tblAdminInf != null) {
			return true; 
		} else {
			//未登录 跳转到登录页面
			throw new SessionTimeoutException();// 返回到配置文件中定义的路径
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

}