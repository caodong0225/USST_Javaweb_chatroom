package top.caodong0225.usst_noteboard.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 *     留言详细视图对象
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
@Getter
@Setter
public class MessageDetailedVO {
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final UserVO createdBy;
    public MessageDetailedVO(
            String title,
            String content,
            LocalDateTime createdAt,
            UserVO createdBy) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}
