package cn.luvletter.exception;

/**
 * @author Zephyr Ji
 * @ Description: token失效异常
 * @ Date 2018/2/26
 */
public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super();
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
