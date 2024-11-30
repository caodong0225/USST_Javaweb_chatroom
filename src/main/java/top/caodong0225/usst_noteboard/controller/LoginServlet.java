package top.caodong0225.usst_noteboard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.caodong0225.usst_noteboard.entity.User;
import top.caodong0225.usst_noteboard.service.UserService;
import top.caodong0225.usst_noteboard.service.impl.UserServiceImpl;

import java.io.IOException;

/**
 * <p>
 *     登录Servlet
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
@WebServlet(name = "LoginServlet", value = "/page/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            response.sendRedirect(request.getContextPath() + "/page/message");
            return;
        }

        // 自动填充用户名和密码
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    request.setAttribute("savedUsername", cookie.getValue());
                }
                if ("password".equals(cookie.getName())) {
                    request.setAttribute("savedPassword", cookie.getValue());
                }
            }
        }

        request.getRequestDispatcher("/page/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            response.sendRedirect(request.getContextPath() + "/page/message");
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe"); // 获取记住我选项

        if (username == null || username.isEmpty()) {
            request.setAttribute("error", "用户名不能为空");
            request.getRequestDispatcher("/page/login.jsp").forward(request, response);
            return;
        }
        if (password == null || password.isEmpty()) {
            request.setAttribute("error", "密码不能为空");
            request.getRequestDispatcher("/page/login.jsp").forward(request, response);
            return;
        }

        try {
            user = userService.login(username, password);
            request.getSession().setAttribute("user", user);

            // 处理记住我逻辑
            Cookie usernameCookie;
            Cookie passwordCookie;
            if ("on".equals(rememberMe)) {
                usernameCookie = new Cookie("username", username);
                passwordCookie = new Cookie("password", password);
                usernameCookie.setMaxAge(7 * 24 * 60 * 60); // 保存 7 天
                passwordCookie.setMaxAge(7 * 24 * 60 * 60);
            } else {
                // 清除之前的 Cookie
                usernameCookie = new Cookie("username", "");
                passwordCookie = new Cookie("password", "");
                usernameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);
            }
            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);

            response.sendRedirect(request.getContextPath() + "/page/message");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/page/login.jsp").forward(request, response);
        }
    }
}
