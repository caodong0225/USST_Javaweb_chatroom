package top.caodong0225.usst_noteboard.service.impl;

import top.caodong0225.usst_noteboard.dao.MessageDAO;
import top.caodong0225.usst_noteboard.dao.UserDAO;
import top.caodong0225.usst_noteboard.dao.impl.MessageDAOImpl;
import top.caodong0225.usst_noteboard.dao.impl.UserDAOImpl;
import top.caodong0225.usst_noteboard.entity.Message;
import top.caodong0225.usst_noteboard.entity.User;
import top.caodong0225.usst_noteboard.service.MessageService;
import top.caodong0225.usst_noteboard.vo.MessageDetailedVO;
import top.caodong0225.usst_noteboard.vo.MessageGeneralVO;
import top.caodong0225.usst_noteboard.vo.UserVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 留言服务实现
 * </p>
 *
 * @author jyzxc
 * @since 2024-11-30
 */
public class MessageServiceImpl implements MessageService {
    private final MessageDAO messageDAO = new MessageDAOImpl();
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<MessageGeneralVO> listAllMessages() {
        List<MessageGeneralVO> messageGeneralVOList = new ArrayList<>();
        try {
            messageDAO.queryMessages().forEach(message -> {
                User creator;
                try {
                    creator = userDAO.queryUserById(message.getCreatedBy());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                messageGeneralVOList.add(
                        new MessageGeneralVO(message.getId(), message.getTitle(), message.getCreatedAt(),new UserVO(creator.getId(), creator.getUsername()))
                );
            });
            return messageGeneralVOList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean addMessage(Integer userId, String title, String content) {
        try {
            User user = userDAO.queryUserById(userId);
            if (user == null) {
                throw new RuntimeException("用户未找到");
            }
            return messageDAO.addMessage(new Message(title, content, user.getId()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public MessageDetailedVO getMessageById(Integer id) {
        try {
            Message message = messageDAO.getMessageById(id);
            if (message == null) {
                throw new RuntimeException("消息未找到");
            }
            User creator = userDAO.queryUserById(message.getCreatedBy());
            return new MessageDetailedVO(
                    message.getId(),
                    message.getTitle(),
                    message.getContent(),
                    message.getCreatedAt(),
                    new UserVO(creator.getId(), creator.getUsername())
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
