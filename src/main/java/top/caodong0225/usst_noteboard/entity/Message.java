package top.caodong0225.usst_noteboard.entity;

import lombok.Getter;
import lombok.Setter;

import java.beans.JavaBean;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *     留言实体类
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
@JavaBean
@Getter
@Setter
public class Message implements Serializable {
    private final Integer id;
    private final String title;
    private final String content;
    private final Integer createdBy;
    private final LocalDateTime createdAt;
    public Message(
            Integer id,
            String title,
            String content,
            Integer createdBy,
            LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
    public Message(
            String title,
            String content,
            int createdBy) {
        this(null, title, content, createdBy, LocalDateTime.now());
    }
}
