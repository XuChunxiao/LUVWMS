package cn.luvletter;

import cn.luvletter.bean.ApiResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
}
