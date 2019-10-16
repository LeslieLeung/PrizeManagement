package model;

import com.google.gson.Gson;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.security.MessageDigest;

public class Token {
    private String jwt;

    public static String string2SHA256StrJava(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

//    public String getToken() {
//
//    }

    public static void main(String[] args) {
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String secret_ = string2SHA256StrJava("ameow");
        byte[] secret = "2162d3e65a421bc0ac76ae5acfe29c650becb73f2a9b8ce57941036331b1aaa8".getBytes();
        SecretKey key = Keys.hmacShaKeyFor(secret);

        String jws = Jwts.builder()
                .setHeaderParam("kid", "123456")
                .setSubject("111")
                .setIssuer("ameow")
                .setNotBefore(new Date())
                .claim("weisha", "wozhidaole")
                .signWith(key)
                .compact();

        System.out.println(jws);

        Jws<Claims> jwsR;
        try {
            jwsR = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jws);
            System.out.println(jwsR);
            System.out.println(jwsR.getBody().get("weisha"));

        } catch (JwtException ex) {
            System.out.println("???");
        }

    }
}
