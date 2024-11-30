package top.caodong0225.usst_noteboard.service.impl;

import top.caodong0225.usst_noteboard.dao.UserDAO;
import top.caodong0225.usst_noteboard.dao.impl.UserDAOImpl;
import top.caodong0225.usst_noteboard.entity.User;
import top.caodong0225.usst_noteboard.service.UserService;
import top.caodong0225.usst_noteboard.util.SafetyUtils;
import top.caodong0225.usst_noteboard.vo.UserVO;

/**
 * <p>
 *     用户服务实现类
 * </p>
 * @author jyzxc
 */
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public UserVO login(String username, String password) {
        try {
            User user = userDAO.queryUserByUsername(username);
            if (user == null) {
                throw new RuntimeException("用户未找到");
            }
            if (!SafetyUtils.checkBCrypt(password, user.getPassword())) {
                throw new RuntimeException("密码错误");
            }
            // 移除密码不显示在前端，保证数据安全
            return new UserVO(user.getId(), user.getUsername());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public UserVO register(String username, String password) throws RuntimeException {
        try {
            boolean user = userDAO.addUser(new User(username, SafetyUtils.doBCrypt(password)));
            if (!user) {
                throw new RuntimeException("用户已注册");
            }
            return login(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
