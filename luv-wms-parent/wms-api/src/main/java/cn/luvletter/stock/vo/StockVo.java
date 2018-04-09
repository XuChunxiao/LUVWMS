package cn.luvletter.stock.vo;

import cn.luvletter.base.model.Goods;
import cn.luvletter.stock.model.Stock;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/9
 */
public class StockVo extends Stock {

    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
