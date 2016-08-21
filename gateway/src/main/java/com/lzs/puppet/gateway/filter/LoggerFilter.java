package com.lzs.puppet.gateway.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("myFilter")  
public class LoggerFilter implements Filter{

	private Logger logger = LoggerFactory.getLogger(LoggerFilter.class);
	
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
		logger.info("request uri("+req.getQueryString()+"):" + req.getRequestURI() + "?" + req.getQueryString());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}  

}
