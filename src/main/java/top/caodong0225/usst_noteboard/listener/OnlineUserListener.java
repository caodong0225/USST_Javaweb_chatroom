package top.caodong0225.usst_noteboard.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import top.caodong0225.usst_noteboard.util.ServletContextUtils;
import top.caodong0225.usst_noteboard.vo.UserVO;


/**
 * @author jyzxc
 * @since 2024-12-9
 */
@WebListener
public class OnlineUserListener implements HttpSessionAttributeListener {

    private final ServletContextUtils servletContextUtils = new ServletContextUtils();

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        // 当属性被添加到 session 时触发
        ServletContext context = event.getSession().getServletContext();
        UserVO user = (UserVO) event.getValue();
        servletContextUtils.addOnlineUser(context, user);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        // 当属性从 session 中移除时触发
        ServletContext context = event.getSession().getServletContext();
        UserVO user = (UserVO) event.getValue();
        servletContextUtils.removeOnlineUser(context, user);
    }
}
