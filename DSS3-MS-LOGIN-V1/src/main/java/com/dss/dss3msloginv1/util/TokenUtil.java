package com.dss.dss3msloginv1.util;
import com.dss.dss3msloginv1.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;


    public class TokenUtil {

        public String generateToken(User user) {

            Calendar now = Calendar.getInstance();
            Date issueTime = now.getTime();
            now.add(Calendar.MINUTE, 15);
            Date expiryTime = now.getTime();

            return Jwts.builder()
                    .setIssuer("User Service")
                    .setSubject(user.getUserId())
                    .setIssuedAt(issueTime)
                    .setExpiration(expiryTime)
                    .claim("user", user.getEmail())
                    .signWith(SignatureAlgorithm.HS512, "BE587273FD55CB2264CD638FDC4E8B37C165425D1BED662A5E5E9506FB0BB371")
                    .compact();
        }
    }

