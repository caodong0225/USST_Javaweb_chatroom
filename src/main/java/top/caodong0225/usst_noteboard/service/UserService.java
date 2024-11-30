package top.caodong0225.usst_noteboard.service;

import top.caodong0225.usst_noteboard.entity.User;

/**
 * @author jyzxc
 * @since 2024-11-30
 */
public interface UserService {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否登录成功
     */
    User login(String username, String password);
    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否注册成功
     */
    User register(String username, String password) throws RuntimeException;
}