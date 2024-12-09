package top.caodong0225.usst_noteboard.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.caodong0225.usst_noteboard.util.SafetyUtils;
import top.caodong0225.usst_noteboard.vo.UserVO;

import java.io.IOException;

/**
 * @author jyzxc
 * @since 2024-12-9
 */
@WebFilter(urlPatterns = {"/page/messages"})
public class DirtyWordFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化过滤器（如果需要）
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getParameter("title") != null && request.getParameter("content") != null) {
            String title = SafetyUtils.xssFilter(request.getParameter("title"));
            String content = SafetyUtils.xssFilter(request.getParameter("content"));
            title = SafetyUtils.dirtyWordFiler(title);
            content = SafetyUtils.dirtyWordFiler(content);
            // 更改Parameter
            request.setAttribute("title", title);
            request.setAttribute("content", content);
        }

        // 如果用户已登录，则继续执行后续的过滤器链
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 清理资源（如果需要）
    }
}
