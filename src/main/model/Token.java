package model;

import com.google.gson.Gson;
import dao.StudentDAO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class Token {
    private String jwt;
    final private SecretKey key = Keys.hmacShaKeyFor("2162d3e65a421bc0ac76ae5acfe29c650becb73f2a9b8ce57941036331b1aaa8".getBytes()); //key

    public String getToken(String number) {
        //先获取用户信息
        Student student = new Student();
        student = (new StudentDAO().getInfo(number));

        Claims claims = Jwts.claims();
        claims.put("number", student.number);
        claims.put("name", student.name);

        String jwt = Jwts.builder()
                .setIssuer("com.ameow")
                .setSubject("student")
                .setNotBefore(new Date())
                .setClaims(claims)
                .signWith(key)
                .compact();
        return jwt;
    }

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
