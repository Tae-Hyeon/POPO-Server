package com.fortice.popo.global.util;

import com.fortice.popo.domain.model.User;
import com.fortice.popo.global.common.Token;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.Date;

@NoArgsConstructor
public class JwtUtil {
    @Value("${server.jwt.secret}")
    private String SECRET;

    public String makeToken(User user) {
        Date now = new Date();
        Date timeout = new Date(now.getTime() + Duration.ofMinutes(30).toMillis());
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("Fortice")
                .setIssuedAt(now)
                .setExpiration(timeout)
                .claim("id", encodeId(user.getId()))
                .claim("email", user.getEmail())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

//    public Token decodeToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//    }

    private String encodeId(Integer id) {
        String encodedId = Integer.toString(id);
        return encodedId;
    }

    private Integer decodeId(String encodedId) {
        Integer id = Integer.parseInt(encodedId);
        return id;
    }
}
