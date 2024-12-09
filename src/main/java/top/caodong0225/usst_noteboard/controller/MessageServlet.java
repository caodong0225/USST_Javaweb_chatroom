package top.caodong0225.usst_noteboard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.caodong0225.usst_noteboard.service.MessageService;
import top.caodong0225.usst_noteboard.service.impl.MessageServiceImpl;
import top.caodong0225.usst_noteboard.vo.MessageGeneralVO;
import top.caodong0225.usst_noteboard.vo.UserVO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 *     消息Servlet
 * </p>
 * @author jyzxc
 * @since 2024-12-1
 */
@WebServlet(name = "MessageServlet", value = "/page/messages")
public class MessageServlet extends HttpServlet {
    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        messageService = new MessageServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MessageGeneralVO> messages = null;
        try {
            messages = messageService.listAllMessages(request.getSession().getServletContext());
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
        }
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/page/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserVO user = (UserVO) request.getSession().getAttribute("user");
        String title = (String) request.getAttribute("title");
        String content = (String) request.getAttribute("content");
        if(user == null)
        {
            request.getRequestDispatcher("/page/login.jsp").forward(request, response);
            return;
        }

        if (title == null || content == null || title.trim().isEmpty() || content.trim().isEmpty()) {
            request.setAttribute("error", "消息标题或消息内容不能为空");
            try {
                request.setAttribute("messages", messageService.listAllMessages(request.getSession().getServletContext()));
            } catch (SQLException e) {
                request.setAttribute("error", e.getMessage());
            }
            request.getRequestDispatcher("/page/list.jsp").forward(request, response);
            return;
        }

        try {
            boolean ret = messageService.addMessage(user.getId(), title.trim(), content.trim());
            if (!ret) {
                request.setAttribute("error", "添加消息失败");
            }
        } catch (RuntimeException | SQLException e) {
            request.setAttribute("error", e.getMessage());
        }

        try {
            request.setAttribute("messages", messageService.listAllMessages(request.getSession().getServletContext()));
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("/page/list.jsp").forward(request, response);
    }
}
