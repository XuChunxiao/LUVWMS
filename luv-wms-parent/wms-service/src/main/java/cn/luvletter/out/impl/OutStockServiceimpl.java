package cn.luvletter.out.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.in.model.InStock;
import cn.luvletter.in.model.InStockDtl;
import cn.luvletter.in.model.InStockDtlExample;
import cn.luvletter.out.OutStockService;
import cn.luvletter.out.dao.OutStockDtlMapper;
import cn.luvletter.out.dao.OutStockMapper;
import cn.luvletter.out.model.OutStock;
import cn.luvletter.out.model.OutStockDtl;
import cn.luvletter.out.model.OutStockDtlExample;
import cn.luvletter.out.model.OutStockExample;
import cn.luvletter.util.WMSUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/3/29
 */
@Service
public class OutStockServiceimpl implements OutStockService {
    @Autowired
    private OutStockMapper outStockMapper;
    @Autowired
    private OutStockDtlMapper outStockDtlMapper;
    @Autowired
    private WMSUtil wmsUtil;
    @Override
    public ApiResult getOutStock(HttpServletRequest httpServletRequest) {
        final String currWNo = wmsUtil.getCurrWNo(httpServletRequest);
        String outStockNo = httpServletRequest.getParameter("outStockNo");
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        OutStockExample outStockExample = new OutStockExample();
        OutStockExample.Criteria criteria = outStockExample.createCriteria();
        if(StringUtils.isNotBlank(outStockNo)){
            criteria.andOutStockNoEqualTo(outStockNo);
        }
        criteria.andWarehouseNoEqualTo(currWNo);
        outStockExample.setStart(start);
        outStockExample.setLimit(limit);
        List<OutStock> outStocks = outStockMapper.selectByExample(outStockExample);
        ApiResult apiResult = new ApiResult();
        apiResult.setData(outStocks);
        return apiResult;
    }

    @Override
    public ApiResult getOutStockDtl(String id, HttpServletRequest httpServletRequest) {
        ApiResult apiResult = new ApiResult();
        if(StringUtils.isBlank(id)|| !NumberUtils.isDigits(id)){
            apiResult.isFalse().setMessage("id不能为空");
            return apiResult;
        }
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        OutStockExample outStockExample = new OutStockExample();
        outStockExample.createCriteria().andIdEqualTo(Long.valueOf(id));
        List<OutStock> outStocks = outStockMapper.selectByExample(outStockExample);
        if(outStocks == null || outStocks.size()==0){
            apiResult.isFalse().setMessage("系统未找到此出库单详情");
            return apiResult;
        }
        String outStockNo = outStocks.get(0).getOutStockNo();
        OutStockDtlExample outStockDtlExample = new OutStockDtlExample();
        outStockDtlExample.createCriteria().andOutStockNoEqualTo(outStockNo);
        List<OutStockDtl> outStockDtls = outStockDtlMapper.selectByExample(outStockDtlExample);
        apiResult.setData(outStockDtls);
        return apiResult;
    }
}
