package com.lzs.puppet.demo.app.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.model.manage.ManageUser;

@Component("defaultFilter")  
public class DefaultFilter implements Filter{

	private Logger logger = LoggerFactory.getLogger(DefaultFilter.class);
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)arg0;
		printRequest(req);
		try{
			arg2.doFilter(arg0, arg1);
		}catch(Throwable t){
			logger.error("Some thing terrible has happen.",t);
		}
	}

	private void printRequest(HttpServletRequest req) {
		if(req.getQueryString() != null){
			logger.info("request uri("+req.getRemoteAddr()+"):" + req.getRequestURI() + "?" + req.getQueryString());
		}else{
			logger.info("request uri("+req.getRemoteAddr()+"):" + req.getRequestURI());
		}
		logger.info("session_id:" + req.getSession().getId());
//		HttpSession session = req.getSession();
//		ManageUser user = (ManageUser) session.getAttribute(Constant.MANAGE_LOGIN_USER);
//		if(user != null){
//			logger.info("request user:" + user.getName());
//		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}  

}
