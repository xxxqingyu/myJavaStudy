package org.smart4j.smart_framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.smart_framework.bean.Data;
import org.smart4j.smart_framework.bean.Handler;
import org.smart4j.smart_framework.bean.Param;
import org.smart4j.smart_framework.bean.View;
import org.smart4j.smart_framework.helper.BeanHelper;
import org.smart4j.smart_framework.helper.ConfigHelper;
import org.smart4j.smart_framework.helper.ControllerHelper;
import org.smart4j.smart_framework.util.CodecUtil;
import org.smart4j.smart_framework.util.JsonUtil;
import org.smart4j.smart_framework.util.ReflectionUtil;
import org.smart4j.smart_framework.util.StreamUtil;
import org.smart4j.smart_framework.util.StringUtil;

@WebServlet(urlPatterns="/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取请求方法与请求路径
		String requestMethod = req.getMethod().toLowerCase();
		String requestPath=req.getPathInfo();
		// 获取Action处理器
		Handler handler=ControllerHelper.getHandler(requestMethod, requestPath);
		if (handler!=null) {
			// 获取Controller类及其Bean实例
			Class<?> controllerClass=handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			// 创建请求参数对象
			Map<String, Object> paramMap=new HashMap<>();
			Enumeration<String> paramNames=req.getParameterNames();
			while (paramNames.hasMoreElements()) {
				String paramName = (String) paramNames.nextElement();
				String paramValue=req.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
			if (StringUtil.isNotEmpty(body)) {
				String[] params=StringUtil.splitString(body, "&");
				if (ArrayUtils.isNotEmpty(params)) {
					for (String param : params) {
						String[] array= StringUtil.splitString(param, "=");
						if (ArrayUtils.isNotEmpty(array)&&array.length==2) {
							String paramName=array[0];
							String paramValue=array[1];
							paramMap.put(paramName, paramValue);
						}
					}
				}
			}
			Param param =new Param(paramMap);
			Object result;
			//调用Action
			Method actionMethod=handler.getActionMethod();
			if(param.isEmpty()){
				result=ReflectionUtil.invokeMethod(controllerBean, actionMethod);
			}else{
				result=ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
			}
			//处理Action方法返回值
			if (result instanceof View) {
				// 返回JSP页面
				View view =(View)result;
				String path=view.getPath();
				if (StringUtil.isNotEmpty(path)) {
					if (path.startsWith("/")) {
						resp.sendRedirect(req.getContextPath()+path);
					}else{
						Map<String, Object> model=view.getModel();
						for (Map.Entry<String, Object> entry : model.entrySet()) {
							req.setAttribute(entry.getKey(), entry.getValue());
						}
						req.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(req, resp);
					}					
				}
			}else if(result instanceof Data){
				// 返回Json
				Data data=(Data) result;
				Object model=data.getModel();
				if (model!=null) {
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
					PrintWriter writer=resp.getWriter();
					String json = JsonUtil.toJson(model);
					writer.write(json);
					writer.flush();
					writer.close();
				}
			}
		}
		// TODO Auto-generated method stub
		super.service(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 初始化相关Helper类
		HelperLoader.init();
		// 获取ServletContext对象
		ServletContext servletContext=config.getServletContext();
		// 注册处理Jsp得Servlet
		ServletRegistration jspServlet=servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
		// 注册处理静态资源得默认Servlet
		ServletRegistration defaultServlet=servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppBaseAssetPath()+"*");
	}
	
}
