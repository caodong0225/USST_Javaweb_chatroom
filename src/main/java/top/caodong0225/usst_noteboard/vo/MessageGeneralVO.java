package top.caodong0225.usst_noteboard.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 *     留言视图对象
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
@Getter
@Setter
public class MessageGeneralVO {
    private final Integer id;
    private final String title;
    private final LocalDateTime createdAt;
    private final UserVO createdBy;
    private final Boolean isOnline;
    public MessageGeneralVO(
            Integer id,
            String title,
            LocalDateTime createdAt,
            UserVO createdBy,
            Boolean isOnline) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.isOnline = isOnline;
    }
}
