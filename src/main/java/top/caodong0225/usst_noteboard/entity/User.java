package top.caodong0225.usst_noteboard.entity;

import lombok.Getter;
import lombok.Setter;

import java.beans.JavaBean;
import java.io.Serializable;

/**
 * <p>
 *     用户实体类
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */

@JavaBean
@Getter
@Setter
public class User implements Serializable {
    private final Integer id;
    private final String username;
    private final String password;
    public User(
            Integer id,
            String username,
            String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public User(
            String username,
            String password) {
        this.id = null;
        this.username = username;
        this.password = password;
    }
}
