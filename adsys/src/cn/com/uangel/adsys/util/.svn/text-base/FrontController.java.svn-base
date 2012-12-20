package cn.com.uangel.adsys.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 中央控制器:试用时需配置ctrl.properties文件
 * 
 * @author Tree
 * 
 */
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Properties p = new Properties();
	private static List<String> ctrlNames;
	private static List<String> suffixs;
	private static Map<String, Object> controllers;

	static {
		controllers = new HashMap<String, Object>();
		InputStream is = FrontController.class.getClassLoader().getResourceAsStream("ctrl.properties");
		try {
			p.load(is);
			String classname = p.getProperty("classname");
			String[] classnames = classname.split(",");
			String suffix = p.getProperty("suffix");
			String[] sufs = suffix.split(",");
			ctrlNames = new ArrayList<String>();
			suffixs = new ArrayList<String>();
			for (int i = 0; i < classnames.length; i++) {
				ctrlNames.add(classnames[i]);
				suffixs.add(sufs[i]);
				try {
					Class<?> clazz = Class.forName("cn.com.uangel.adsys.ctrl." + ctrlNames.get(i));
					Object ctrl = clazz.newInstance();
					controllers.put(sufs[i], ctrl);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			System.out.println("加载属性文件失败！");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
		if (path != null && path.contains("adsys")) {
			basePath += path.substring(1) + "/";
		}

		String pathInfo = request.getPathInfo().substring(1);
		String suffix = pathInfo.substring(pathInfo.lastIndexOf('.') + 1);
		for (int i = 0; i < suffixs.size(); i++) {
			if (suffixs.get(i).equals(suffix)) {
				try {
					Object ctrl = controllers.get(suffix);
					Method method = ctrl.getClass().getDeclaredMethod(pathInfo.substring(0, pathInfo.lastIndexOf(".")),
							HttpServletRequest.class, HttpServletResponse.class);
					if (method != null) {
						String returnUrl = (String) method.invoke(ctrl, request, response);
						String[] url = returnUrl.split(":");
						if ("1".equals(url[0])) {
							request.getRequestDispatcher("/" + url[1]).forward(request, response);
						} else if ("2".equals(url[0])) {
							response.sendRedirect(basePath + url[1]);
						} else if ("3".equals(url[0])) {
							// Ajax
						} else {
							System.out.println("返回字符串错误: " + returnUrl);
						}
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
