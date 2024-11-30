package top.caodong0225.usst_noteboard.dao.impl;

import top.caodong0225.usst_noteboard.dao.UserDAO;
import top.caodong0225.usst_noteboard.entity.User;
import top.caodong0225.usst_noteboard.manager.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * <p>
 *     用户DAO实现类
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public User queryUserByUsername(String username) throws SQLException {
        try (Connection conn = DatabaseConnectionManager.getConnection()) {
            final String sql = "SELECT id, username, password FROM user WHERE username = ? LIMIT 1";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
            return null;
        }
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        if (this.queryUserByUsername(user.getUsername()) != null) {
            return false;
        }
        try (Connection conn = DatabaseConnectionManager.getConnection()) {
            final String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
            // 放置sql注入
            PreparedStatement ps = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            int result = ps.executeUpdate();

            if (result == 1) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    @Override
    public User queryUserById(Integer id) throws SQLException {
        try (Connection conn = DatabaseConnectionManager.getConnection()) {
            final String sql = "SELECT id, username, password FROM user WHERE id = ? LIMIT 1";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }

            return null;
        }
    }
}
