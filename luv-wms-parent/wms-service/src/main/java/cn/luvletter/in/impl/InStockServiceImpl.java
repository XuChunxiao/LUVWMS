package cn.luvletter.in.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.in.InStockService;
import cn.luvletter.in.dao.InStockDtlMapper;
import cn.luvletter.in.dao.InStockMapper;
import cn.luvletter.util.WMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
        String inStockNo = httpServletRequest.getParameter("inStockNo");
        String inStockState = httpServletRequest.getParameter("inStockState");
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);

        return null;
    }

    @Override
    public ApiResult getInDtl(String id, HttpServletRequest httpServletRequest) {
        return null;
    }
}
