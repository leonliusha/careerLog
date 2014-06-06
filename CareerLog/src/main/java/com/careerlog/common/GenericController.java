package com.careerlog.common;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
	
	private static ThreadLocal<HttpServletRequest> request_threadLocal = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> response_threadLocal = new ThreadLocal<HttpServletResponse>();
	
	public static void setRequest(HttpServletRequest request){
		request_threadLocal.set(request);
	}
	
	/*
	 * Convenience method to get the request
	 * @return current request 
	 */
	protected static HttpServletRequest getRequest(){
		return request_threadLocal.get();
	}
	
	public static void removeRequest(){
		request_threadLocal.remove();
	}
	
	public static void setResponse(HttpServletResponse response){
		response_threadLocal.set(response);
	}
	
	/*
	 * Convenience method to get the response
	 * @return current response
	 */
	protected static HttpServletResponse getResponse(){
		return response_threadLocal.get();
	}
	
	public static void removeResponse(){
		response_threadLocal.remove();
	}
}
