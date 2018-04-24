import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/19
 */
public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {
    static {
        try {
            Log4jConfigurer.initLogging("D:\\code\\JAVA\\毕业设计\\WMS\\luv-server\\src\\main\\resources\\log4j.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }
    public JUnit4ClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }
}
