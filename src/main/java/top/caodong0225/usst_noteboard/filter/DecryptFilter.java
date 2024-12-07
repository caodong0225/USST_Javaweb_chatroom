package top.caodong0225.usst_noteboard.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.caodong0225.usst_noteboard.util.DecryptUtils;
import top.caodong0225.usst_noteboard.vo.UserVO;

import java.io.IOException;

/**
 * @author jyzxc
 * @since 2024-12-7
 */
@WebFilter(urlPatterns = {"/page/login", "/page/register"})
public class DecryptFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化过滤器（如果需要）
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        UserVO user = (UserVO) httpRequest.getSession().getAttribute("user");
        if (user != null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/page/message");
            return;
        }
        if(httpRequest.getParameter("encryptedUsername")!=null){
            httpRequest.setAttribute("username",DecryptUtils.decrypt(httpRequest.getParameter("encryptedUsername")).strip());
        }
        if(httpRequest.getParameter("encryptedPassword")!=null){
            httpRequest.setAttribute("password",DecryptUtils.decrypt(httpRequest.getParameter("encryptedPassword")).strip());
        }

        // 如果用户已登录，则继续执行后续的过滤器链
        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
        // 清理资源（如果需要）
    }
}
