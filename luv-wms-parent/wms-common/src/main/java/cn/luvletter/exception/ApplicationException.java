package cn.luvletter.exception;

/**
 * @author Zephyr Ji
 * @ Description: 应用异常
 * @ Date 2018/2/9
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }
}
