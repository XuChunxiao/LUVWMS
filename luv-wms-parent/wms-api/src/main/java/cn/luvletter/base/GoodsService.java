package cn.luvletter.base;

import cn.luvletter.base.model.Goods;
import cn.luvletter.base.vo.GoodsSerarchVo;
import cn.luvletter.bean.ApiResult;

import javax.servlet.http.HttpServletRequest;
/**
 * @author Zephyr Ji
 * @ Description: 商品
 * @ Date 2018/3/28
 */
public interface GoodsService {
    /**
     * @Description: 获取商品列表
     * @Date: 14:13 2018/3/28
     */
    ApiResult getGoods(GoodsSerarchVo serarchVo, HttpServletRequest httpServletRequest);

    /**
     * @Description: 更新商品
     * @Date: 14:14 2018/3/28
     */
    ApiResult update(Goods goods, HttpServletRequest httpServletRequest);

    /**
     * @Description: 删除商品
     * @Date: 14:14 2018/3/28
     */
    ApiResult delById(String id, HttpServletRequest httpServletRequest);

    /**
     * @Description: 新增商品
     * @Date: 14:15 2018/3/28
     */
    ApiResult sava(Goods goods, HttpServletRequest httpServletRequest);
}
