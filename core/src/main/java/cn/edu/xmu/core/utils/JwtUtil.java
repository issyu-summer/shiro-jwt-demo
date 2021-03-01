package cn.edu.xmu.core.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author summer
 * @date 2021/3/1 18:19
 * jwt工具
 */
public class JwtUtil {

    /**
     * 密钥
     */
    private static final String SECRET="Authorization-Secret";

    /**
     * 生成token
     */
    public static String generateToken(){
        return null;
    }

    /**
     * 验证token
     */
    public static boolean verify(){
        return true;
    }

    /**
     * static会不会被取出进而被破解？
     */
    @Data
    @Accessors(chain = true)
    public static class User{
        private String username;
        private String password;
    }
}
