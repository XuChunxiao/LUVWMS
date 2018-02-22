package cn.luvletter.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/11
 */
public class ResponseUtil {

    public static void returnJson(HttpServletResponse response, Object o){
        JSONObject jsonObject = (JSONObject) JSON.toJSON(o);
        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(jsonObject.toJSONString());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(out!=null){
                out.close();
            }
        }
    }
}
