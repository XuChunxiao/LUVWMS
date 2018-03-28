package cn.luvletter.base.vo;

import java.io.UnsupportedEncodingException;

/**
 * @author Zephyr Ji
 * @ Description: 商品列表查询参数
 * @ Date 2018/3/28
 */
public class GoodsSerarchVo {
    private String goodsName;
    private String pn;
    private String goodsColor;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) throws UnsupportedEncodingException {
        this.goodsName = new String(goodsName.getBytes("ISO-8859-1"), "UTF-8");
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) throws UnsupportedEncodingException {
        this.goodsColor = new String(goodsColor.getBytes("ISO-8859-1"), "UTF-8");
    }

    @Override
    public String toString() {
        return "GoodsSerarchVo{" +
                "goodsName='" + goodsName + '\'' +
                ", pn='" + pn + '\'' +
                ", goodsColor='" + goodsColor + '\'' +
                '}';
    }
}
