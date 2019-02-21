package com.micolor.commoncore.jwt;

import com.micolor.commoncore.statics.EnumUtil;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Project:logistics-single
 * Package:com.liheng.shock.commoncore.jwt
 *
 * @Author: Evangoe
 * @Description:
 * @Date:31/05/17
 * @Modified:
 */
public class JwtHelper {
    private JwtHelper() {
    }

    private static Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    public static EnumUtil.MessageStatus checkToken(String jsonWebToken, String base64Security) {
        try {
            Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return EnumUtil.MessageStatus.OK;
        } catch (ExpiredJwtException ex) {
            logger.error("内部错误，原因：{}", ex.getMessage());
            return EnumUtil.MessageStatus.TIMEOUT;
        } catch (Exception ex) {
            logger.error("内部错误，原因：{}", ex.getMessage());
            return EnumUtil.MessageStatus.ERROR;
        }
    }

    public static Claims parseJWT(String jsonWebToken, String base64Security){
        try
        {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
        }
        catch(Exception ex)
        {
            logger.error("内部错误，原因：{}",ex.getMessage());
            return null;
        }
    }

    public static String createJWT(String name, String userId, String role,
                                   String audience, String issuer, long tTLMillis, String base64Security)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("role", role)
                .claim("unique_name", name)
                .claim("userid", userId)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (tTLMillis >= 0) {
            long expMillis = nowMillis + tTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }
}
