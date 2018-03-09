package cn.luvletter.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/3/9
 */
@Component
public class PrintBean implements BeanFactoryAware ,ApplicationContextAware{
    private DefaultListableBeanFactory beanFactory;
    private ApplicationContext applicationContext;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.printThisContentBean();
    }

    private void printThisContentBean(){
        String[] beanDefinitionNames = this.beanFactory.getBeanDefinitionNames();
        for (String beanDefName : beanDefinitionNames) {
            System.out.println("SpringBean==========" + beanDefName);
        }
    }
}
