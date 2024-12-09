package top.caodong0225.usst_noteboard.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     安全工具类
 * </p>
 * @author jyzxc
 * @since 2024-11-30
 */
public class SafetyUtils {
    /**
     * BCrypt加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    public static String doBCrypt(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    /**
     * BCrypt校验
     *
     * @param password 校验的密码
     * @param hash     加密后的密码
     * @return 校验结果
     */
    public static boolean checkBCrypt(String password, String hash) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
        return result.verified;
    }

    /**
     * XSS过滤
     *
     * @param str 过滤的字符串
     * @return 过滤后的字符串
     */
    public static String xssFilter(String str) {
        return str
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#39;")
                .replaceAll("\n", "<br/>")
                .replaceAll("\r", "");
    }

    public static String dirtyWordFiler(String str) {
        List<String> dirties = new ArrayList<String>();

        dirties.add("你妈");
        dirties.add("王八蛋");
        dirties.add("二狗子");
        dirties.add("傻逼");
        dirties.add("操你妈");
        dirties.add("垃圾");
        dirties.add("狗屎");
        dirties.add("狗日的");
        for (String dirty : dirties) {
            str = str.replaceAll(dirty, "***");
        }
        return str;
    }
}
