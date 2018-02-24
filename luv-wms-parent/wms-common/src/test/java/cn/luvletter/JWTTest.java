package cn.luvletter;

import cn.luvletter.util.JWTUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zephyr Ji
 * @ Description: TODO
 * @ Date 2018/2/13
 */
public class JWTTest {
    public static final String SECRET = "LUVLETTER";
    @Test
    public void test01() throws UnsupportedEncodingException {
        String token = JWTTest.createToken();
        System.out.println("token:"+token);
        Map<String, Claim> stringClaimMap = JWTTest.verifyToken(token);
        for(Map.Entry<String,Claim> entry:stringClaimMap.entrySet()){
            System.out.print(entry.getKey());
            System.out.print(":");
            System.out.println(entry.getValue().asDate());
        }
        String expireToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJvcmciOiJjbi5sdXZsZXR0ZXIiLCJuYW1lIjoiemVwaHlyIiwiZXhwIjoxNTE4NDk0MTc5LCJpYXQiOjE1MTg0OTQxMTksImFnZSI6MjB9.Po_WHt9u5NJM71PbNtnSK0_PspVWsnPlfGp4d-zDNkU";
        JWTUtil.validateToken(expireToken,SECRET);
    }
    /**
     * @Description:  创建token
     * @Date: 11:50 2018/2/13
     */
    public static String createToken() throws UnsupportedEncodingException {
        //签发时间
        Date iatDate = new Date();
        //过期时间 1分钟过期
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,1);
        Date expiresDate = nowTime.getTime();

        Map<String,Object> map = new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        String token = JWT.create()
                .withHeader(map)//header
                .withClaim("name","zephyr")//payload
                .withClaim("age",20)
                .withClaim("org","cn.luvletter")
                .withExpiresAt(expiresDate)//过期时间，大于签发时间
                .withIssuedAt(iatDate)//签发时间
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }
    /**
     * @Description: 解密
     * @Date: 11:50 2018/2/13
     */
    public static Map<String,Claim> verifyToken(String token) throws UnsupportedEncodingException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try{
            jwt = jwtVerifier.verify(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jwt.getClaims();
    }
}
