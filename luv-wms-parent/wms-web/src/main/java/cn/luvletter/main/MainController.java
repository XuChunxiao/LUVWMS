package cn.luvletter.main;

import cn.luvletter.base.BaseController;
import cn.luvletter.bean.ApiResult;
import cn.luvletter.sys.api.SysService;
import cn.luvletter.util.WMSUtil;
import com.sun.deploy.association.utility.AppUtility;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/9
 */
@Controller
@RequestMapping("/")
public class MainController extends BaseController {
    @Autowired
    private WMSUtil wmsUtil;
    /**
     * @Description: 根据文件名获取七牛上传凭证
     * @Date: 17:02 2018/3/28
     */
    @GetMapping("/upToken")
    @ResponseBody
    public ApiResult getUpToken(@RequestParam String fileName){
        final ApiResult apiResult = new ApiResult();
        if(StringUtils.isBlank(fileName)||!fileName.contains(".jpg")){
            apiResult.isFalse().setMessage("文件名格式不正确");
            return apiResult;
        }
//        fileName = fileName.substring(0,fileName.lastIndexOf("."));
        String qiuNiuUpToken = wmsUtil.getQiuNiuUpToken(fileName);
        apiResult.setData(qiuNiuUpToken);
        return apiResult;
    }
}
