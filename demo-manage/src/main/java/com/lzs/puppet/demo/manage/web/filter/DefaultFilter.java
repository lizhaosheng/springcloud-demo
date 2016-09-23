package com.lzs.puppet.demo.manage.web.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.lzs.puppet.demo.base.constant.Constant;
import com.lzs.puppet.demo.base.util.StringMatchHelper;
import com.lzs.puppet.demo.model.manage.ManageUser;

@Component("defaultFilter")  
public class DefaultFilter implements Filter{

	private Logger logger = LoggerFactory.getLogger(DefaultFilter.class);
	
	private static Set<String> nologinSet = new HashSet<String>();
	static{
		nologinSet.add("/hello");
		nologinSet.add("/login");
		nologinSet.add("/doLogin");
	}
	
	private StringMatchHelper stringMatchHelper ;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		
		printRequest(req);
		
		String path = req.getRequestURI();
		String contextPath = req.getContextPath();

		int idx = contextPath.length();
		String endPath = path.substring(idx);
		if (endPath.startsWith("//")) {
			endPath = endPath.substring(1);
		}
		
		if(stringMatchHelper.match(endPath)){
			arg2.doFilter(arg0, arg1);
			return;
		}
		try{
			ManageUser user = (ManageUser) req.getSession().getAttribute(Constant.MANAGE_LOGIN_USER);
			if(user == null){
				boolean isAjaxRequest = isAjaxRequest(req);  
                if (isAjaxRequest)  
                {  
                    res.setCharacterEncoding("UTF-8");  
                    res.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");  
                    return ;  
                }  
                res.sendRedirect("/login");  
                return;  
			}
			logger.info("request user:" + user.getName());
			arg2.doFilter(arg0, arg1);
		}catch(Throwable t){
			logger.error("Some thing terrible has happen.",t);
		}
	}

	private void printRequest(HttpServletRequest req) {
//		Enumeration<String> it = req.getHeaderNames();
//		String name = null;
//		while(it.hasMoreElements()){
//			name = it.nextElement();
//			logger.info("HEADER=====>name:" + name + "    ,value:" + req.getHeader(name));
//		}
		if(req.getQueryString() != null){
			logger.info("request uri("+req.getRemoteAddr()+"):" + req.getRequestURI() + "?" + req.getQueryString());
		}else{
			logger.info("request uri("+req.getRemoteAddr()+"):" + req.getRequestURI());
		}
		logger.info("session_id:" + req.getSession().getId());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		stringMatchHelper = new StringMatchHelper(nologinSet);
	}  
	
	/** 判断是否为Ajax请求  
     * <功能详细描述> 
     * @param request 
     * @return 是true, 否false  
     * @see [类、类#方法、类#成员] 
     */  
    public static boolean isAjaxRequest(HttpServletRequest request)  
    {  
        String header = request.getHeader("X-Requested-With");   
        if (header != null && "XMLHttpRequest".equals(header))   
            return true;   
        else   
            return false;    
    }  
     
}
