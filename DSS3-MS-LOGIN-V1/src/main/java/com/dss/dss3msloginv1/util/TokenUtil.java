package com.dss.dss3msloginv1.util;
import com.dss.dss3msloginv1.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

    @Component
    public class TokenUtil {

        @Value("${jwt.secret}")
        private String jwtSecret;

        @Value("${jwt.expiration}")
        private int jwtExpirationInMin;

        public String generateToken(User user) {

            Calendar now = Calendar.getInstance();
            Date issueTime = now.getTime();
            now.add(Calendar.MINUTE, jwtExpirationInMin);
            Date expiryTime = now.getTime();


            return Jwts.builder()
                    .setIssuer("User Service")
                    .setSubject(user.getUserId().toString())
                    .setIssuedAt(issueTime)
                    .setExpiration(expiryTime)
                    .claim("user", user.getUserId())
                    .claim("email", user.getEmail())
                    .claim("phone", user.getPhoneNumber())
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
        }
    }

