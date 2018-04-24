package cn.luvletter.sys.impl;

import cn.luvletter.base.impl.StorageLocationServiceImpl;
import cn.luvletter.bean.ApiResult;
import cn.luvletter.constant.WMSConstant;
import cn.luvletter.sys.api.OperatorService;
import cn.luvletter.sys.dao.OperatorMapper;
import cn.luvletter.sys.model.Operator;
import cn.luvletter.sys.model.OperatorExample;
import cn.luvletter.sys.vo.OperatorVo;
import cn.luvletter.util.AESUtil;
import cn.luvletter.util.DateUtil;
import cn.luvletter.util.WMSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/20
 */
@Service
public class OperatorServiceImpl implements OperatorService {
    private final Logger logger = LoggerFactory.getLogger(OperatorServiceImpl.class);
    @Autowired
    private OperatorMapper operatorMapper;
    @Autowired
    private WMSUtil wmsUtil;
    @Override
    public ApiResult getOperators(HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        OperatorExample operatorExample = new OperatorExample();
        operatorExample.setStart(start);
        operatorExample.setLimit(limit);
        List<OperatorVo> operators = operatorMapper.selectByExampleAndJoinRole(operatorExample);
        apiResult.setData(operators);
        apiResult.setTotal(operatorMapper.countByExample(operatorExample));
        return apiResult;
    }

    @Override
    public ApiResult update(Operator operator, HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public ApiResult delById(String id, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        final String currUser = wmsUtil.getCurrUser(httpServletRequest);
        Long lId = Long.valueOf(id);
        Operator operator = operatorMapper.selectByPrimaryKey(lId);
        if(operator == null){
            apiResult.isFalse().setMessage("系统未找到此人员信息");
            return apiResult;
        }
        int i = operatorMapper.deleteByPrimaryKey(lId);
        if(i == 1){
            logger.info("删除人员信息：id："+id+"用户："+currUser);
            apiResult.setMessage("删除成功");
        }else {
            apiResult.isFalse().setMessage("删除失败");
        }
        return apiResult;
    }

    @Override
    public ApiResult sava(Operator operator, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        final String currUser = wmsUtil.getCurrUser(httpServletRequest);
        operator.setPassword(AESUtil.AESEncode(WMSConstant.DEFAULT_PWD));
        operator.setOprtName(currUser);
        operator.setOprtTime(DateUtil.now());
        int i = operatorMapper.insertSelective(operator);
        if(i == 1){
            logger.info("新增人员信息，new operator :"+operator.toString()+"用户："+currUser);
            apiResult.setMessage("新增成功");
        }else {
            apiResult.isFalse().setMessage("新增失败");
        }
        return apiResult;
    }
}
