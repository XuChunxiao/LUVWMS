package cn.luvletter.base.impl;

import cn.luvletter.base.GoodsService;
import cn.luvletter.base.dao.GoodsMapper;
import cn.luvletter.base.model.Goods;
import cn.luvletter.base.model.GoodsExample;
import cn.luvletter.base.vo.GoodsSerarchVo;
import cn.luvletter.bean.ApiResult;
import cn.luvletter.util.DateUtil;
import cn.luvletter.util.WMSUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/3/28
 */
@Service
public class GoodsServiceImpl implements GoodsService{

    private final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private WMSUtil wmsUtil;
    @Override
    public ApiResult getGoods(GoodsSerarchVo serarchVo, HttpServletRequest httpServletRequest) {
        final String currWNo = wmsUtil.getCurrWNo(httpServletRequest);
        final ApiResult apiResult = new ApiResult();
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setStart(start);
        goodsExample.setLimit(limit);
        GoodsExample.Criteria criteria = goodsExample.createCriteria().andWarehouseNoEqualTo(currWNo);
        String goodsName = serarchVo.getGoodsName();
        if(StringUtils.isNotBlank(goodsName)){
            criteria.andGoodsNameLike("%"+goodsName+"%");
        }
        String goodsColor = serarchVo.getGoodsColor();
        if(StringUtils.isNotBlank(goodsColor)){
            criteria.andGoodsColorLike("%"+goodsColor+"%");
        }
        String pn = serarchVo.getPn();
        if(StringUtils.isNotBlank(pn)){
            criteria.andPnLike("%"+pn+"%");
        }
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        apiResult.setData(goods);
        return apiResult;
    }

    @Override
    public ApiResult update(Goods goods, HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public ApiResult delById(String id, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        final String currUser = wmsUtil.getCurrUser(httpServletRequest);
        Long lId = Long.valueOf(id);
        Goods goods = goodsMapper.selectByPrimaryKey(lId);
        if(goods == null){
            apiResult.isFalse().setMessage("系统未找到此商品");
            return apiResult;
        }
        int i = goodsMapper.deleteByPrimaryKey(lId);
        if(i == 1){
            logger.info("删除商品：id："+id+"用户："+currUser);
            apiResult.setMessage("删除成功");
        }else {
            apiResult.isFalse().setMessage("删除失败");
        }
        return apiResult;
    }

    @Override
    public ApiResult sava(Goods goods, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        final String currWNo = wmsUtil.getCurrWNo(httpServletRequest);
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andWarehouseNoEqualTo(currWNo).andPnEqualTo(goods.getPn());
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if(goodsList!=null && goodsList.size()!=0){
            apiResult.isFalse().setMessage("仓库已存在此PN的商品");
            return apiResult;
        }
        goods.setWarehouseNo(currWNo);
        goods.setGmtCreate(DateUtil.now());
        goods.setGoodsNo(wmsUtil.getSerialNumber());
        int i = goodsMapper.insertSelective(goods);
        if(i == 1){
            logger.info("新增商品 "+goods.toString());
            apiResult.setMessage("新增成功");
        }else{
            apiResult.isFalse().setMessage("新增失败");
        }

        return apiResult;
    }
}
