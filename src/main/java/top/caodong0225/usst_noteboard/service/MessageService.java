package top.caodong0225.usst_noteboard.service;

import jakarta.servlet.ServletContext;
import top.caodong0225.usst_noteboard.vo.MessageDetailedVO;
import top.caodong0225.usst_noteboard.vo.MessageGeneralVO;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 *     留言服务
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
public interface MessageService {
    /**
     * 列出所有留言
     *
     * @return 留言列表
     */
    List<MessageGeneralVO> listAllMessages(ServletContext context) throws SQLException;
    /**
     * 添加留言
     *
     * @param userId 用户ID
     * @param title 标题
     * @param content 内容
     * @return 是否添加成功
     */
    boolean addMessage(Integer userId, String title, String content) throws SQLException;
    /**
     * 通过id查询留言
     *
     * @param id 留言id
     * @return 留言
     */
    MessageDetailedVO getMessageById(Integer id) throws RuntimeException;
}
