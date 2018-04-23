package cn.luvletter.security;

import cn.luvletter.util.AESUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Zephyr Ji
 * @ Description: AES加密和比较
 * @ Date 2018/2/10
 */
public class AESPasswordEncoder implements PasswordEncoder{
    @Override
    public String encode(CharSequence rawPassword) {
        return AESUtil.AESEncode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return AESUtil.checkpw(rawPassword.toString(),encodedPassword);
    }
}
