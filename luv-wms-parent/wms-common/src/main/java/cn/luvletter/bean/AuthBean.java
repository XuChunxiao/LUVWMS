package cn.luvletter.bean;

import java.util.List;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/24
 */
public class AuthBean {
    private String url;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AuthBean{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
