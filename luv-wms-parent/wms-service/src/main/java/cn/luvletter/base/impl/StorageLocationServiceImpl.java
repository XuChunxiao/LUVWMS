package cn.luvletter.base.impl;

import cn.luvletter.base.StorageLocationService;
import cn.luvletter.base.dao.StorageLocationMapper;
import cn.luvletter.base.model.StorageLocation;
import cn.luvletter.base.model.StorageLocationExample;
import cn.luvletter.base.vo.StorageLocationVo;
import cn.luvletter.bean.ApiResult;
import cn.luvletter.util.DateUtil;
import cn.luvletter.util.WMSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Zephyr Ji
 * @ Description: 库位Serviceimpl
 * @ Date 2018/3/6
 */
@Service
public class StorageLocationServiceImpl implements StorageLocationService {
    private final Logger logger = LoggerFactory.getLogger(StorageLocationServiceImpl.class);
    @Autowired
    private StorageLocationMapper storageLocationMapper;
    @Autowired
    private WMSUtil wmsUtil;
    @Override
    public ApiResult getStorageArea(HttpServletRequest httpServletRequest) {
        final String currWNo = wmsUtil.getCurrWNo(httpServletRequest);
        final ApiResult apiResult = new ApiResult();
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        StorageLocationExample storageLocationExample = new StorageLocationExample();
        storageLocationExample.setStart(start);
        storageLocationExample.setLimit(limit);
        storageLocationExample.createCriteria().andWarehouseNoEqualTo(currWNo);
        List<StorageLocation> storageLocations = storageLocationMapper.selectByExample(storageLocationExample);
        apiResult.setData(storageLocations);
        apiResult.setTotal(storageLocationMapper.countByExample(storageLocationExample));
        return apiResult;
    }

    @Override
    public ApiResult delById(String id, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        final String currUser = wmsUtil.getCurrUser(httpServletRequest);
        Long lId = Long.valueOf(id);
        StorageLocation storageLocation = storageLocationMapper.selectByPrimaryKey(lId);
        if(storageLocation == null){
            apiResult.isFalse().setMessage("系统未找到此库位");
            return apiResult;
        }
        int i = storageLocationMapper.deleteByPrimaryKey(lId);
        if(i == 1){
            logger.info("删除库位：id："+id+"用户："+currUser);
            apiResult.setMessage("删除成功");
        }else {
            apiResult.isFalse().setMessage("删除失败");
        }
        return apiResult;
    }

    @Override
    public ApiResult sava(StorageLocationVo storageLocationVo, HttpServletRequest httpServletRequest) {
        //todo 批量新增库位
        return null;
    }

    @Override
    public ApiResult update(StorageLocation storageLocation, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        Long id = storageLocation.getId();
        if(id == null || id == 0){
            apiResult.isFalse().setMessage("id不能为空");
            return apiResult;

        }
        storageLocation.setGmtModified(DateUtil.now());
        storageLocationMapper.updateByPrimaryKeySelective(storageLocation);
        apiResult.setMessage("更新成功");
        return apiResult;
    }
}
