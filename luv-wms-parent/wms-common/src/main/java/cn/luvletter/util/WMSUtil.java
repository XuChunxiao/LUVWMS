package cn.luvletter.util;

import cn.luvletter.constant.WMSConstant;
import cn.luvletter.exception.ApplicationException;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Zephyr Ji
 * @ Description: WMS工具类
 * @ Date 2018/3/28
 */
@Component
public class WMSUtil {



    @Resource
    private RedisUtil redisUtil;

    /**
     * @Description: 根据账号获取绑定仓库
     * @Date: 9:38 2018/3/28
     */
    public String getCurrWNo(String account){
        String wNo = (String) redisUtil.hGet(WMSConstant.R_WAREHOUSENO_M, account);
        if(StringUtils.isEmpty(wNo)){
            throw new ApplicationException("当前登录用户:"+account+"未绑定仓库");
        }
        return wNo;
    }
    public String getCurrWNo(HttpServletRequest httpServletRequest){
//        String currUser = this.getCurrUser(httpServletRequest);
//        return this.getCurrWNo(currUser);
        return "1";
    }
    /**
     * @Description: 从token中获取当前登录用户
     * @Date: 9:47 2018/3/28
     */
    public String getCurrUser(HttpServletRequest httpServletRequest){
//        final String token = JWTUtil.getToken(httpServletRequest);
//        if(StringUtils.isEmpty(token)) {
//            throw new ApplicationException("获取当前登录用户失败");
//        }
//        return JWTUtil.getUsernameFromToken(token);
        return "admin";
    }
    /**
     * @Description: 获取分页开始
     * @Date: 10:05 2018/3/28
     */
    public String getStart(HttpServletRequest httpServletRequest){
        String page = httpServletRequest.getParameter("start");
        if(StringUtils.isEmpty(page)|| !NumberUtils.isDigits(page)){
            page = WMSConstant.DEFAULT_START;
        }
        return page;
    }
    /**
     * @Description: 获取分页大小
     * @Date: 10:05 2018/3/28
     */
    public String getLimit(HttpServletRequest httpServletRequest){
        String page = httpServletRequest.getParameter("limit");
        if(StringUtils.isEmpty(page)|| !NumberUtils.isDigits(page)){
            page = WMSConstant.DEFAULT_LIMIT;
        }
        return page;
    }
    /**
     * @Description: 生成15位编号
     * @Date: 14:38 2018/3/28
     */
    public String getSerialNumber(){
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
        sb.append(date);
        sb.append("0");
        sb.append(RandomStringUtils.randomNumeric(2));
        sb.append("0");
        sb.append(seconds);
        sb.append(RandomStringUtils.randomNumeric(2));
        return sb.toString();
    }

    /**
     * @Description: 获取七牛上传凭证
     * @Date: 16:23 2018/3/28
     */
    public String getQiuNiuUpToken(String fileName){
        Auth auth = Auth.create(WMSConstant.ACCESSKEY, WMSConstant.SECRETKEY);
        StringMap putPolicy = new StringMap();
        //String imgName = fileName.substring(0,fileName.lastIndexOf("."));
        String imgURL = WMSConstant.CDN_URL_PER+"/"+fileName;
        putPolicy.put("returnBody","{\"code\":\"0\",\"imgURL\":\""+imgURL+"\",\"size\":\"$(fsize)\"}");
        long expireSeconds = 60;
        return auth.uploadToken(WMSConstant.BUCKET,fileName,expireSeconds,putPolicy);
    }

    /**
     * @Description:获取真实IP地址
     * @Date: 16:01 2018/4/17
     */
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
