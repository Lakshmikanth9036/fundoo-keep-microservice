package com.bridgelabz.fundookeep.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundookeep.exception.TokenException;
import com.bridgelabz.fundookeep.repository.RedisService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtils {

	@Autowired
	private RedisService redisService;

	private final String SECRET = "kanth@123";
	private final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	public String generateToken(Long id) {
		return Jwts.builder().setSubject(String.valueOf(id))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	public String generateUserToken(Long id) {
		return Jwts.builder().setSubject(String.valueOf(id)).signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	public Long decodeToken(String jwt) {
		
			if (redisService.getToken(jwt) != null)
				return redisService.getToken(jwt);
			else {
				try {
				System.out.println(redisService);
				Claims claim = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt).getBody();
				Long id = Long.parseLong(claim.getSubject());
				redisService.putToken(jwt, id);
				return id;
				}
				 catch (TokenException e) {
						throw new TokenException(HttpStatus.REQUEST_TIMEOUT.value(), "HttpStatus.REQUEST_TIMEOUT.toString()");
					}
			}
	}
}
