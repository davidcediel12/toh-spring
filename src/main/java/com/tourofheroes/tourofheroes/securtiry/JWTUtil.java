package com.tourofheroes.tourofheroes.securtiry;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;


@Service
public class JWTUtil {
	
	@Value("${jwtSecret}")
	private  String KEY = "uniqueKey";
	@Value("${jwtExpirationMs}")
	private  Integer jwtExpirationMs = 1000 * 60 * 60 * 2;
	@Value("${jwtRefreshExpirationMs}")
	private  Integer jwtRefreshExpirationMs = 1000 * 60 * 60 * 3;
	
	public String generateToken(UserDetails userDetails) {
		System.out.println("Exp " + jwtExpirationMs);
		System.out.println("Exp " + jwtRefreshExpirationMs);
		System.out.println("Key " + KEY);
		return Jwts.builder().setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS256, KEY).compact();
	}
	
	
	// Verifica que el token sea valido
	public boolean validateToken(String token, UserDetails userDetails) {
		return userDetails.getUsername().equals(getUsername(token)) &&
				!isTokenExpired(token);
	}
	
	
	// Verifica que el token no haya expirado
	public boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}
	
	// Obtiene el username que esta en el token
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	// Tokens -> Las partes del token, devuelve un error si el token esta malo
	public Claims getClaims(String token) throws SignatureException{
		// Parsea el JET con la llave que lo firmamos y devuelve los claims
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
	}
}
