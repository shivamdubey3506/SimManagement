package com.example.sim.utility;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final Key key=Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long  expirationMillis=1000 * 60 * 60;

    public String generateToken(String aadhaar) {
        return Jwts.builder()
                .setSubject(aadhaar)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractAadhaar(String token) {
        return Jwts.parserBuilder()
        		.setSigningKey(key)
        		.build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
    	try {
    		Jwts.parserBuilder()
    		.setSigningKey(key)
    		.build()
    		.parseClaimsJws(token);
    		return true;
    	}
    	catch (JwtException | IllegalArgumentException e) {
        System.out.println("Invalid JWT : " + e.getMessage() );
        return false;
    }

    }
}
