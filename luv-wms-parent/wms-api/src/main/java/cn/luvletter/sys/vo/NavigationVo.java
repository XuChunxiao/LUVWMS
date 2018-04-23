package cn.luvletter.sys.vo;

import java.util.List;

/**
 * @author Zephyr
 * @Description: 菜单
 * @Date 2018/4/21
 */
public class NavigationVo {
    /**
     * 标题
     */
    private String text;
    /**
     * 路径
     */
    private String to;
    /**
     * 图标
     */
    private String icon;
    /**
     * 子菜单
     */
    private List<NavigationVo> children;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<NavigationVo> getChildren() {
        return children;
    }

    public void setChildren(List<NavigationVo> children) {
        this.children = children;
    }
}
