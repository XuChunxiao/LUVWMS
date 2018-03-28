package cn.luvletter;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.util.WMSUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/11
 */
public class JSONTest {
    @Test
    public void test01(){
        ApiResult apiResult = new ApiResult().isFalse();
        apiResult.setMessage("test");
        JSONObject o = (JSONObject) JSON.toJSON(apiResult);
        System.out.println(o.toString());
        System.out.println(o.toJSONString());
        String s = JSON.toJSONString(apiResult);
        System.out.println(s);
    }
    @Test
    public void test02(){
        String fileName = "12412341234.jpg";
        if(StringUtils.isBlank(fileName)||!fileName.contains(".jpg")){
            System.out.println(false);
        }
        fileName = fileName.substring(0,fileName.lastIndexOf("."));
        System.out.println(fileName);
    }
}
