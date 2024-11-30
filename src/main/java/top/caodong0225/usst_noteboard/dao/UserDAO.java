package top.caodong0225.usst_noteboard.dao;

import top.caodong0225.usst_noteboard.entity.User;

import java.sql.SQLException;

/**
 * <p>
 *     用户DAO
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
public interface UserDAO {
    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User queryUserByUsername(String username) throws SQLException;
    /**
     * 添加用户
     *
     * @param user 用户
     * @return 添加后的用户
     */
    boolean addUser(User user) throws SQLException;
    /**
     * 通过id查询用户
     *
     * @param id 用户id
     * @return 用户
     */
    User queryUserById(Integer id) throws SQLException;
}
