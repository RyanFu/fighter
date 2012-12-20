package cn.com.uangel.adsys.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpFilter implements Filter {
	protected FilterConfig config;

	@Override
	public final void init(FilterConfig config) throws ServletException {
		this.config = config;
		init();
	}

	public void init() throws ServletException {
	}

	public String getInitParameter(String name) {
		return config.getInitParameter(name);
	}

	public ServletContext getServletContext() {
		return config.getServletContext();
	}

	@Override
	public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
	}

	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException;

	@Override
	public void destroy() {
	}

	protected boolean isStaticResource(String path) {
		if (path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".jpg") || path.endsWith(".jpeg")
				|| path.endsWith(".gif") || path.endsWith("png") || path.endsWith(".html") || path.endsWith(".htm")) {
			return true;
		}

		return false;
	}
}
