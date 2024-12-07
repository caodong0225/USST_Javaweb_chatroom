package top.caodong0225.usst_noteboard.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.caodong0225.usst_noteboard.vo.UserVO;

import java.io.IOException;

/**
 * @author jyzxc
 * @since 2024-12-6
 */
@WebFilter(urlPatterns = {"/page/logout", "/page/message/*"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化过滤器（如果需要）
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 从session中获取用户信息
        UserVO user = (UserVO) httpRequest.getSession().getAttribute("user");

        // 判断用户是否登录，如果没有登录则重定向到登录页面
        if (user == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/page/login");
            return; // 如果用户未登录，则终止后续过滤器链的执行
        }

        // 如果用户已登录，则继续执行后续的过滤器链
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 清理资源（如果需要）
    }
}
