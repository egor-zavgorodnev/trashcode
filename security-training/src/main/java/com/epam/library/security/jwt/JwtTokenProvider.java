package com.epam.library.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

	@Autowired
	private UserDetailsService userDetailsService;

	@Value("${jwt.secretKey}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long validityInMilliseconds;

	@Value("${jwt.header}")
	private String authorizationHeader;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String username) {
		Claims claims = Jwts.claims().setSubject(username); //устанавливаем { "username" : username }
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds * 1000);

		return Jwts.builder()
				.setClaims(claims) // устанавливаем username и роль
				.setIssuedAt(now) // устанавливаем {"iat": now} - время создания токена
				.setExpiration(validity) // устанавливаем {"exp" : validity} - время истечения токена
				.signWith(SignatureAlgorithm.HS256, secretKey).compact(); // установка {"alg" : alg} - алгоритм шифрования
	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token); //дешифруем токен, парсим, получаем набор клаймов
			return !claimsJws.getBody().getExpiration().before(new Date()); //true - если токен не истек
		} catch (JwtException | IllegalArgumentException ex) {
			throw new JwtAuthentificationException("JWT Token is expired or invalid", HttpStatus.UNAUTHORIZED);
		}
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUserName(token)); // получаем UserDetails по юзернейму
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()); // возвращаем объект Authentication
	}

	public String getUserName(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject(); // дешифруем токен, достаем значение поля username в json
	}

	public String resolveToken(HttpServletRequest request) {
		return  request.getHeader(authorizationHeader); //берем токен у хедера
	}
}
