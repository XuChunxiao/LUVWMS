package cn.luvletter.base.impl;

import cn.luvletter.base.StorageAreaService;
import cn.luvletter.base.dao.StorageAreaMapper;
import cn.luvletter.base.model.StorageArea;
import cn.luvletter.base.model.StorageAreaExample;
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
 * @ Description: 库区serviceimpl
 * @ Date 2018/3/6
 */
@Service
public class StorageAreaServiceImpl implements StorageAreaService {
    private final Logger logger = LoggerFactory.getLogger(StorageAreaServiceImpl.class);
    @Autowired
    private StorageAreaMapper storageAreaMapper;
    @Autowired
    private WMSUtil wmsUtil;
    @Override
    public ApiResult getStorageArea(HttpServletRequest httpServletRequest) {
        final String currWNo = wmsUtil.getCurrWNo(httpServletRequest);
        final ApiResult apiResult = new ApiResult();
        String start = wmsUtil.getStart(httpServletRequest);
        String limit = wmsUtil.getLimit(httpServletRequest);
        StorageAreaExample storageAreaExample = new StorageAreaExample();
        storageAreaExample.setStart(start);
        storageAreaExample.setLimit(limit);
        storageAreaExample.createCriteria().andWarehouseNoEqualTo(currWNo);
        storageAreaExample.setOrderByClause("gmt_create desc");
        List<StorageArea> storageAreas = storageAreaMapper.selectByExample(storageAreaExample);
        apiResult.setData(storageAreas);
        apiResult.setTotal(storageAreaMapper.countByExample(storageAreaExample));
        return apiResult;
    }

    @Override
    public ApiResult delById(String id, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        final String currUser = wmsUtil.getCurrUser(httpServletRequest);
        Long lId = Long.valueOf(id);
        StorageArea storageArea = storageAreaMapper.selectByPrimaryKey(lId);
        if(storageArea == null){
            apiResult.isFalse().setMessage("系统未找到此库区");
            return apiResult;
        }
        int i = storageAreaMapper.deleteByPrimaryKey(lId);
        if(i == 1){
            logger.info("删除库区：id："+id+"用户："+currUser);
            apiResult.setMessage("删除成功");
        }else {
            apiResult.isFalse().setMessage("删除失败");
        }
        return apiResult;
    }

    @Override
    public ApiResult sava(StorageArea storageArea, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        final String currUser = wmsUtil.getCurrUser(httpServletRequest);
        final String currWNo = wmsUtil.getCurrWNo(httpServletRequest);
        StorageAreaExample storageAreaExample = new StorageAreaExample();
        storageAreaExample.createCriteria().andWarehouseNoEqualTo(currWNo).andAreaNoEqualTo(storageArea.getAreaNo());
        List<StorageArea> storageAreas = storageAreaMapper.selectByExample(storageAreaExample);
        if(storageAreas!=null && storageAreas.size()!=0){
            apiResult.isFalse().setMessage("仓库已存在此库区编号的库区");
            return apiResult;
        }
        storageArea.setCreateUser(currUser);
        storageArea.setWarehouseNo(currWNo);
        storageArea.setGmtCreate(DateUtil.now());
        int i = storageAreaMapper.insertSelective(storageArea);
        if(i == 1){
            logger.info("新增库区 "+storageArea.toString());
            apiResult.setMessage("新增成功");
        }else{
            apiResult.isFalse().setMessage("新增失败");
        }

        return apiResult;
    }

    @Override
    public ApiResult update(StorageArea storageArea, HttpServletRequest httpServletRequest) {
        final ApiResult apiResult = new ApiResult();
        Long id = storageArea.getId();
        if(id == null || id == 0){
            apiResult.isFalse().setMessage("id不能为空");
            return apiResult;
        }
        storageArea.setGmtModified(DateUtil.now());
        storageAreaMapper.updateByPrimaryKeySelective(storageArea);
        apiResult.setMessage("更新成功");
        return apiResult;
    }
}
