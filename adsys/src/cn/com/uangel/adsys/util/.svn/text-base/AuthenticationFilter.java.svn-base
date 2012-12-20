package cn.com.uangel.adsys.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Tree
 */
public class AuthenticationFilter extends HttpFilter {
	
	/**
	 * 系统的登录页面
	 */
    private String loginPage;
    /**
     * 系统的登录用户
     */
    private String userKey;

    @Override
    public void init() throws ServletException {
        loginPage = getInitParameter("loginPage");
        if (loginPage == null) {
            throw new ServletException("没有配置登录页面！");
        }

        userKey = getInitParameter("userKey");
        if (userKey == null) {
            userKey = "member";
        }
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + loginPage);
        } else {
            Object user = session.getAttribute(userKey);
            if (user == null) {
                response.sendRedirect(request.getContextPath() + loginPage);
            } else {
                chain.doFilter(request, response);
            }
        }
    }
}