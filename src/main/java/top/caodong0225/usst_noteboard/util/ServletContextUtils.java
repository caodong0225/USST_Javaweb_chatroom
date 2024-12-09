package top.caodong0225.usst_noteboard.util;

import jakarta.servlet.ServletContext;
import top.caodong0225.usst_noteboard.vo.UserVO;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jyzxc
 * @since 2024-12-9
 */
public class ServletContextUtils {
    private static final String ONLINE_USERS_KEY = "onlineUsers";

    /**
     * 获取全局在线用户列表，如果没有则初始化一个新列表
     */
    @SuppressWarnings("unchecked")
    public List<UserVO> getOnlineUsers(ServletContext context) {
        // 保证线程安全
        synchronized (context) {
            List<UserVO> onlineUsers = (List<UserVO>) context.getAttribute(ONLINE_USERS_KEY);
            if (onlineUsers == null) {
                onlineUsers = new CopyOnWriteArrayList<>();
                context.setAttribute(ONLINE_USERS_KEY, onlineUsers);
            }
            return onlineUsers;
        }
    }

    /**
     * 添加在线用户
     */
    public void addOnlineUser(ServletContext context, UserVO user) {
        getOnlineUsers(context).add(user);
    }

    /**
     * 删除在线用户
     */
    public void removeOnlineUser(ServletContext context, UserVO user) {
        getOnlineUsers(context).remove(user);
    }
}
