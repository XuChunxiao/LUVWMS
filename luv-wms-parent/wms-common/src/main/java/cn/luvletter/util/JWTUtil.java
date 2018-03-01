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
import org.springframework.security.core.GrantedAuthority;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/13
 */
public class JWTUtil {
   private static final String TOKEN_PREFIX = "Bearer";
   private static final String HEADER_STRING = "Authorization";

    /**
     * @Description:    添加head
     * @Date: 17:37 2018/2/13
     */
    public static String addAuthentication(HttpServletResponse response, AuthenticationBean authenticationBean) throws UnsupportedEncodingException {
       //签发时间
       Date iatDate = new Date();
       //过期时间 1分钟过期
       Calendar nowTime = Calendar.getInstance();
       nowTime.add(Calendar.MINUTE,10);
       Date expiresDate = nowTime.getTime();

       Map<String,Object> map = new HashMap<>();
       map.put("alg","HS256");
       map.put("typ","JWT");
       String token = JWT.create()
               .withHeader(map)//header
               .withClaim("account",authenticationBean.getAccount())//payload
               .withClaim("role","role_"+authenticationBean.getRoleNo())
               .withExpiresAt(expiresDate)//过期时间，大于签发时间
               .withIssuedAt(iatDate)//签发时间
               .sign(Algorithm.HMAC256(authenticationBean.getPassword()));
        response.addHeader(HEADER_STRING,TOKEN_PREFIX+token);
        return token;
   }
   public static void refreshToken(HttpServletResponse response, AuthenticationBean authenticationBean) throws UnsupportedEncodingException {
        addAuthentication(response,authenticationBean);
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
           DecodedJWT jwt = null;
           try{
               jwt = jwtVerifier.verify(token);
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
