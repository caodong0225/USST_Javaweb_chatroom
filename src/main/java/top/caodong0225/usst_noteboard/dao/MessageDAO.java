package top.caodong0225.usst_noteboard.dao;

import top.caodong0225.usst_noteboard.entity.Message;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 *     留言数据访问对象
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
public interface MessageDAO {
    /**
     * 查询所有留言
     * @return 留言列表
     * @throws SQLException SQL异常
     */
    List<Message> queryMessages() throws SQLException;
    /**
     * 添加留言
     * @param message 留言
     * @return 是否添加成功
     * @throws SQLException SQL异常
     */
    boolean addMessage(Message message) throws SQLException;
    /**
     * 通过ID查询留言
     * @param id 留言ID
     * @return 留言
     * @throws SQLException SQL异常
     */
    Message getMessageById(Integer id) throws SQLException;
}
