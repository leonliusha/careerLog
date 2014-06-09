package com.careerlog.common;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.careerlog.ProfileService.entity.User;


/*
 * Implementation of GenericController that	contains convenience methods for subclass,
 * such as, getting the current user and saving message/errors. this class is intended
 * to be a base class for all controller.
 *  
 *  */
public class GenericController {

	/**
	 * Log varibale for all child classes, uses LogFactory.getLog(getClass())
	 */
	protected final transient Logger logger = Logger.getLogger(GenericController.class);
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	/*
	 * Convenience method to get the request
	 * @return current request 
	 */
	protected HttpServletRequest getRequest(){
		request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/*
	 * Convenience method to get the response
	 * @return current response
	 */
	protected HttpServletResponse getResponse(){
		response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		return response;
	}
	
	/*
	 * Convenience method to get the session
	 * @return current session 
	 */
	
	protected HttpSession getSession(){
		return getRequest().getSession();
	}
	
	protected Object getCurrentUser(){
		return getSession().getAttribute("user");
	}
	
	protected void setCurrentUser(User user){
		getSession().setAttribute("user", user);
	}
	
	/*
	 * Velocity template to use for e-mailing
	 */
	protected String templateName;
	protected void setTemplateName(String templateName){
		this.templateName = templateName;
	}
	
	protected String getTemplateName(){
		return this.templateName;
	}
	
	/*******************************************************************
	 *  
	 * the following methods were designed to be used by ThreadLocal
	 * 
	 * ******************************************************************
	 */
	/*private static ThreadLocal<HttpServletRequest> request_threadLocal = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> response_threadLocal = new ThreadLocal<HttpServletResponse>();
	
	public static void setRequest(HttpServletRequest request){
		request_threadLocal.set(request);
	}
		
	protected static HttpServletRequest getRequest(){
		return request_threadLocal.get();
	}
	
	public static void removeRequest(){
		request_threadLocal.remove();
	}
	
	public static void setResponse(HttpServletResponse response){
		response_threadLocal.set(response);
	}
	
	protected static HttpServletResponse getResponse(){
		return response_threadLocal.get();
	}
	
	public static void removeResponse(){
		response_threadLocal.remove();
	}
	*/
	
}
