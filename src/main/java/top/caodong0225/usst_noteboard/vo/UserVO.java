package top.caodong0225.usst_noteboard.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *     用户视图对象
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
@Getter
@Setter
public class UserVO {
    private final Integer id;
    private final String username;
    public UserVO(
            Integer id,
            String username) {
        this.id = id;
        this.username = username;
    }
}
