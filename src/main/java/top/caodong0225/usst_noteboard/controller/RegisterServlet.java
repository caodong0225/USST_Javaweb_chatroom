package top.caodong0225.usst_noteboard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.caodong0225.usst_noteboard.service.UserService;
import top.caodong0225.usst_noteboard.service.impl.UserServiceImpl;
import top.caodong0225.usst_noteboard.vo.UserVO;

import java.io.IOException;

/**
 * <p>
 *     注册Servlet
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
@WebServlet(name = "RegisterServlet", value = "/page/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 若session存有user信息，那么直接跳转到留言页面，否则跳转到注册页面
        UserVO user = (UserVO) request.getSession().getAttribute("user");
        if (user != null) {
            response.sendRedirect(request.getContextPath() + "/page/message");
            return;
        }
        request.getRequestDispatcher("/page/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "用户名或密码不能为空");
            request.getRequestDispatcher("/page/register.jsp").forward(request, response);
            return;
        }

        try {
            UserVO user = userService.register(username, password);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/page/message");
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/page/register.jsp").forward(request, response);
        }
    }
}
