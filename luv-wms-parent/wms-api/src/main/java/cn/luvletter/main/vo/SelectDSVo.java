package cn.luvletter.main.vo;

import java.io.Serializable;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/10
 */
public class SelectDSVo implements Serializable {

    private static final long serialVersionUID = 5616243027392526908L;

    private String label;
    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SelectDSVo{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
