package top.caodong0225.usst_noteboard.dao.impl;

import top.caodong0225.usst_noteboard.dao.MessageDAO;
import top.caodong0225.usst_noteboard.entity.Message;
import top.caodong0225.usst_noteboard.manager.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * <p>
 *     留言DAO实现类
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
public class MessageDAOImpl implements MessageDAO {
    @Override
    public List<Message> queryMessages() throws SQLException {
        List<Message> messages = new ArrayList<>();

        try (Connection conn = DatabaseConnectionManager.getConnection()) {
            final String sql = "SELECT id, title, content, created_by, created_at FROM message ORDER BY created_at DESC;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Message message = new Message(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("created_by"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );

                messages.add(message);
            }
        }

        return messages;
    }

    @Override
    public boolean addMessage(Message message) throws SQLException {
        try (Connection conn = DatabaseConnectionManager.getConnection()) {
            final String sql = "INSERT INTO message (title, content, created_by) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
            ps.setString(1, message.getTitle());
            ps.setString(2, message.getContent());
            ps.setInt(3, message.getCreatedBy());

            int result = ps.executeUpdate();

            return result == 1;
        }
    }

    @Override
    public Message getMessageById(Integer id) throws SQLException {
        try (Connection conn = DatabaseConnectionManager.getConnection()) {
            final String sql = "SELECT id, title, content, created_by, created_at FROM message WHERE id = ? LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Message(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("created_by"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
            }

            return null;
        }
    }
}
