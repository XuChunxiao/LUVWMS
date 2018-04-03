package cn.luvletter.in.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.in.InStockService;
import cn.luvletter.in.dao.InStockDtlMapper;
import cn.luvletter.in.dao.InStockMapper;
import cn.luvletter.in.model.InStock;
import cn.luvletter.in.model.InStockDtl;
import cn.luvletter.in.model.InStockDtlExample;
import cn.luvletter.in.model.InStockExample;
import cn.luvletter.util.WMSUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/3/29
 */
@Service
public class InStockServiceImpl implements InStockService {
    @Autowired
    private InStockMapper inStockMapper;
    @Autowired
    private InStockDtlMapper inStockDtlMapper;
    @Autowired
    private WMSUtil wmsUtil;
    @Override
    public ApiResult getInStock(HttpServletRequest httpServletRequest) {
        final String currWNo = wmsUtil.getCurrWNo(httpServletRequest);
        String inStockNo = httpServletRequest.getParameter("inStockNo");
        String inStockStatus = httpServletRequest.getParameter("inStockStatus");
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        InStockExample inStockExample = new InStockExample();
        InStockExample.Criteria criteria = inStockExample.createCriteria();
        if(StringUtils.isNotBlank(inStockNo)){
            criteria.andInStockNoEqualTo(inStockNo);
        }
        if(StringUtils.isNotBlank(inStockStatus)){
            criteria.andInStockStatusEqualTo(inStockStatus);
        }
        criteria.andWarehouseNoEqualTo(currWNo);
        inStockExample.setStart(start);
        inStockExample.setLimit(limit);
        List<InStock> inStocks = inStockMapper.selectByExample(inStockExample);
        ApiResult apiResult = new ApiResult();
        apiResult.setData(inStocks);
        apiResult.setTotal(inStockMapper.countByExample(inStockExample));
        return apiResult;
    }

    @Override
    public ApiResult getInDtl(String id, HttpServletRequest httpServletRequest) {
        ApiResult apiResult = new ApiResult();
        if(StringUtils.isBlank(id)|| !NumberUtils.isDigits(id)){
            apiResult.isFalse().setMessage("id不能为空");
            return apiResult;
        }
        InStockExample inStockExample = new InStockExample();
        inStockExample.createCriteria().andIdEqualTo(Long.valueOf(id));
        List<InStock> inStocks = inStockMapper.selectByExample(inStockExample);
        if(inStocks == null || inStocks.isEmpty()){
            apiResult.isFalse().setMessage("系统未找到此入库单详情");
            return apiResult;
        }
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        String inStockNo = inStocks.get(0).getInStockNo();
        InStockDtlExample inStockDtlExample = new InStockDtlExample();
        inStockDtlExample.createCriteria().andInStockNoEqualTo(inStockNo);
        inStockDtlExample.setStart(start);
        inStockDtlExample.setLimit(limit);
        List<InStockDtl> inStockDtls = inStockDtlMapper.selectByExample(inStockDtlExample);
        apiResult.setData(inStockDtls);
        apiResult.setTotal(inStockDtlMapper.countByExample(inStockDtlExample));
        return apiResult;
    }

    @Override
    public ApiResult uploadInStock(MultipartFile file, HttpServletRequest httpServletRequest) {
        ApiResult apiResult = new ApiResult();

        return apiResult;
    }
}
