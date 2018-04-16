package cn.luvletter.util;

import cn.luvletter.bean.AuthenticationBean;
import cn.luvletter.exception.ApplicationException;
import cn.luvletter.exception.InvalidTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/13
 */
@Component("jwtUtil")
public class JWTUtil {
   private static final String TOKEN_PREFIX = "Bearer";
   private static final String HEADER_STRING = "Authorization";
   private static final String REDIS_TOKEN_NAME = "auth_token_";
   private static final String REDIS_TOKEN_SECRIET = "LUVLETTER";

   public JWTUtil(){
       System.out.println("实例化JWTUtil");
   }

   @Autowired
   private RedisUtil redisUtil;
   /**
    * @Description: 将刷新token添加到redis中
    * @Date: 11:11 2018/3/6
    */
   public void addRedisRefreshToken(String account){
       String refreshToken = null;
       try {
           refreshToken = generateToken(null, 60*24);
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
       redisUtil.strSet(REDIS_TOKEN_NAME+account,refreshToken,60);
   }
   /**
    * @Description: 生成token
    * @Date: 11:11 2018/3/6
    */
   public static String generateToken(AuthenticationBean authenticationBean, int aging) throws UnsupportedEncodingException {
       //签发时间
       Date iatDate = new Date();
       //过期时间
       Calendar nowTime = Calendar.getInstance();
       nowTime.add(Calendar.MINUTE,aging);
       Date expiresDate = nowTime.getTime();

       Map<String,Object> map = new HashMap<>();
       map.put("alg","HS256");
       map.put("typ","JWT");
       String token = "";
       if(authenticationBean != null){
           token = JWT.create()
                   .withHeader(map)//header
                   .withClaim("account",authenticationBean.getAccount())//payload
                   .withClaim("role","role_"+authenticationBean.getRoleNo())
                   .withExpiresAt(expiresDate)//过期时间，大于签发时间
                   .withIssuedAt(iatDate)//签发时间
                   .sign(Algorithm.HMAC256(authenticationBean.getPassword()));
       }else{
           token = JWT.create()
                   .withHeader(map)//header
                   .withExpiresAt(expiresDate)//过期时间，大于签发时间
                   .withIssuedAt(iatDate)//签发时间
                   .sign(Algorithm.HMAC256(REDIS_TOKEN_SECRIET));
       }
       return token;
   }
    /**
     * @Description:    添加head
     * @Date: 17:37 2018/2/13
     */
    public static String addAuthentication(HttpServletResponse response, AuthenticationBean authenticationBean) {
        String token = null;
        try {
            token = generateToken(authenticationBean,30);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addHeader(HEADER_STRING,TOKEN_PREFIX+token);
        return token;
   }

   /**
    * @Description: 刷新token ，刷新token过期则返回false
    * @Date: 15:28 2018/4/10
    */
   public boolean refreshToken(HttpServletResponse response, AuthenticationBean authenticationBean) {
       String refreshToken = (String) redisUtil.strGet(REDIS_TOKEN_NAME+authenticationBean.getAccount());
       if(StringUtils.isEmpty(refreshToken)){
           return false;
       }
       Date expiresAt = JWT.decode(refreshToken).getExpiresAt();
       if(expiresAt.compareTo(DateUtil.now())<0) {
           return false;
       }
       addAuthentication(response,authenticationBean);
       return true;
   }

   /**
    * @Description: 从request拿到token
    * @Date: 22:26 2018/2/24
    */
   public static String getToken(HttpServletRequest request) {
       String header = request.getHeader(HEADER_STRING);
       if(StringUtils.isEmpty(header)){
           return null;
       }
       return header.replaceAll(TOKEN_PREFIX,"").trim();
   }

   /**
    * @Description: 检验token是否有效
    * @Date: 22:27 2018/2/24
    */
   public static boolean validateToken(String token,String secret) throws UnsupportedEncodingException {
       if(token != null){
           Date expiresAt = JWT.decode(token).getExpiresAt();
           if(expiresAt.compareTo(DateUtil.now())<0) {
               throw new InvalidTokenException("token expire!");
           }
           JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
           try{
               jwtVerifier.verify(token);
           }catch (Exception e){
              e.printStackTrace();
               return false;
           }
           return true;
       }
       return false;
   }

    /**
     * @Description: 从token获取账号
     * @Date: 22:27 2018/2/24
     */
    public static String getUsernameFromToken(String authHead) {
            return JWT.decode(authHead).getClaim("account").asString();
    }


    public static String authenticationToString(Collection<? extends GrantedAuthority> authentications){
        StringBuilder res = new StringBuilder();
        int v = 1;
        for(GrantedAuthority grantedAuthority : authentications){
            res.append(grantedAuthority.getAuthority());
            v++;
            if(v<authentications.size()){
                res.append(",");
            }
        }
        return res.toString();
    }
}
