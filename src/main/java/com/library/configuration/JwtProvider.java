package com.library.configuration;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public String generateToken(Authentication authentication) {
		Collections <?extends GrantedAuthority> authorities= authentication.getAuthorities();
		String roles=popularAuthorities(authorities);
		return Jwts.builder().issuedAt(new Date())
				.expiration(new Date(new Date().getTime()+8640000))
				.claim("email", authentication.getName())
				.claim("authorities", roles)
				.signWith(key)
				.compact();
				
}
	
	public String getemailFromJwtToken(String jwt) {
		jwt=jwt.substring(7);
		Claims claims=Jwts.parser()JwtParserBuilder.
				verifyWith(key)
				.build()
				.parseSignedClaims(jwt)getPayload();
		return String.valueOf(claims.get("email"));
		)
	}
	
	private String populateAuthorities(Collection<?extends GrantedAuthority> authorities) {
		Set<String> auths= new HashSet<>();
		
		for(GrantedAuthority authority: authorities) {
			auths.add(authority.getAuthority());
		}
		return String.join("", auths);
	}
