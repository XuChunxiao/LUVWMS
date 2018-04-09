package cn.luvletter.stock.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.out.model.OutStock;
import cn.luvletter.out.model.OutStockDtlExample;
import cn.luvletter.stock.StockService;
import cn.luvletter.stock.dao.StockDtlMapper;
import cn.luvletter.stock.dao.StockMapper;
import cn.luvletter.stock.model.Stock;
import cn.luvletter.stock.model.StockDtl;
import cn.luvletter.stock.model.StockDtlExample;
import cn.luvletter.stock.model.StockExample;
import cn.luvletter.stock.vo.StockVo;
import cn.luvletter.util.WMSUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zephyr
 * @Description: 库存
 * @Date 2018/4/9
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private StockDtlMapper stockDtlMapper;
    @Autowired
    private WMSUtil wmsUtil;
    @Override
    public ApiResult getStock(HttpServletRequest httpServletRequest) {
        final String currWNo = wmsUtil.getCurrWNo(httpServletRequest);
        String goodsName = httpServletRequest.getParameter("goodsName");
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        Map<String,String> params = new HashMap<>();
        if(StringUtils.isNotBlank(goodsName)){
            params.put("goodsName",goodsName);
        }
        params.put("warehouseNo",currWNo);
        params.put("start",start);
        params.put("limit",limit);
        List<StockVo> stockVoList = stockMapper.selectStockVo(params);
        ApiResult apiResult = new ApiResult();
        apiResult.setData(stockVoList);
        apiResult.setTotal(stockMapper.countStockVo(params));
        return apiResult;
    }

    @Override
    public ApiResult getStockDtl(String id, HttpServletRequest httpServletRequest) {
        ApiResult apiResult = new ApiResult();
        if(StringUtils.isBlank(id)|| !NumberUtils.isDigits(id)){
            apiResult.isFalse().setMessage("id不能为空");
            return apiResult;
        }
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        StockExample stockExample = new StockExample();
        stockExample.createCriteria().andIdEqualTo(Long.valueOf(id));
        List<Stock> stocks = stockMapper.selectByExample(stockExample);
        if(stocks == null || stocks.isEmpty()){
            apiResult.isFalse().setMessage("系统未找到此库存详情");
            return apiResult;
        }
        String goodsNo = stocks.get(0).getGoodsNo();
        StockDtlExample stockDtlExample = new StockDtlExample();
        stockDtlExample.createCriteria().andGoodsNoEqualTo(goodsNo);
        stockDtlExample.setStart(start);
        stockDtlExample.setLimit(limit);
        List<StockDtl> stockDtls = stockDtlMapper.selectByExample(stockDtlExample);
        apiResult.setData(stockDtls);
        apiResult.setTotal(stockDtlMapper.countByExample(stockDtlExample));
        return apiResult;
    }
}
