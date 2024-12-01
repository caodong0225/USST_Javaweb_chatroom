package top.caodong0225.usst_noteboard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.caodong0225.usst_noteboard.service.MessageService;
import top.caodong0225.usst_noteboard.service.impl.MessageServiceImpl;
import top.caodong0225.usst_noteboard.vo.MessageDetailedVO;
import top.caodong0225.usst_noteboard.vo.UserVO;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * <p>
 *     消息详情Servlet
 * </p>
 * @author jyzxc
 * @since 2024-12-1
 */
@WebServlet(name = "MessageDetailServlet", value = "/page/message/*")
public class MessageDetailServlet extends HttpServlet {
    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        messageService = new MessageServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserVO user = (UserVO) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/page/login");
            return;
        }

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        if (pathParts.length == 0) {
            response.sendRedirect(request.getContextPath() + "/page/message");
            return;
        }

        if (this.messageService == null) {
            Logger.getLogger("MessageDetailServlet").warning("messageService is null");
            response.sendError(500);
            return;
        }

        try {
            Integer id = Integer.parseInt(pathParts[1]);
            MessageDetailedVO message = this.messageService.getMessageById(id);
            request.setAttribute("message", message);
        } catch (Exception e) {
            Logger.getLogger("MessageDetailServlet").warning(e.getMessage());
            response.sendError(404);
            return;
        }

        request.getRequestDispatcher("/page/message.jsp").forward(request, response);
    }
}
